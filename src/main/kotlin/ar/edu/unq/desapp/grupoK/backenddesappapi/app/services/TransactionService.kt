package ar.edu.unq.desapp.grupoK.backenddesappapi.app.services

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.OperationType
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.State
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.TransactionActivity
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.dto.CreateTransactionDTO
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.dto.TransactionDetailsDTO
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.repositories.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.lang.Exception

@Repository
@Service
class TransactionService {
    @Autowired
    private var transactionRepo: TransactionRepository? = null
    @Autowired
    private var userService: UserService? = null

    fun CreateTransaction(dto:CreateTransactionDTO): Int {
        var entity = TransactionActivity(dto)
        return  transactionRepo!!.save(entity).id
    }

    fun GetTransactionWithId(id:Long):TransactionActivity {
        if (transactionRepo!!.existsById(id.toInt())) {
            return transactionRepo!!.findById(id.toInt()).get()
       }
        else {
            throw Exception("Element with id: $id not found")
        }
    }

    fun GetTransactionDetailsDTOForId(id:Long):TransactionDetailsDTO{
        var entity = GetTransactionWithId(id)
        var price = entity.nominalAmount.toBigDecimal() * entity.unitPriceARS.toBigDecimal()
        var creatorEmail = if (entity.operationType == OperationType.BUY) entity.buyerEmail!! else entity.sellerEmail!!
        var creatorUser = userService!!.findUsersByEmail(creatorEmail)[0]
        var buyerEmail = if(entity.buyerEmail==null){""} else entity.buyerEmail!!
        var sellerEmail = if(entity.sellerEmail==null){""} else entity.sellerEmail!!

        return TransactionDetailsDTO(entity.id,entity.time, entity.crypto, entity.nominalAmount, entity.unitPriceARS, price.toString(), creatorUser.name,
            creatorUser.lastName, creatorUser.reputation.toString(),sellerEmail,buyerEmail, entity.operationType.toString(),entity.stateHistory.stateUpdates.last().state.toString())
    }

    fun GetAllTransactions():List<TransactionActivity>{
        return transactionRepo!!.findAll().toList()
    }

    fun ApplyToTransaction(id: Long, userEmail: String): String {
        var entity = GetTransactionWithId(id)
        var result = ""
        if (entity.buyerEmail != userEmail && entity.sellerEmail != userEmail) {
            if (entity.operationType == OperationType.BUY) {
                entity.sellerEmail = userEmail
                result = userService!!.findUsersByEmail(entity.buyerEmail!!)[0].walletAddress
            } else {
                entity.buyerEmail = userEmail
                result = userService!!.findUsersByEmail(entity.sellerEmail!!)[0].cvu
            }
            entity.stateHistory.AddState(State.APPLIED)
            transactionRepo!!.save(entity)
            return result
        } else {
            throw Exception("Applying user cannot be same user as creator user")
        }
    }

    fun ChangeTransactionState(id: Long, newState: State, userUpdaterEmail: String)
    {
        var entity = GetTransactionWithId(id)

        if (newState == State.CLOSED) {
            if (entity.stateHistory.ContainsState(State.TRANSFERENCE_DONE)) {
                entity.stateHistory.AddState(newState)
                userService!!.updateUsersFromCompletedTransaction(entity)
            } else {
                throw Exception("Cannot change state to 'CLOSED' because there is no record of 'TRANSFERENCE_DONE'")
            }
        } else {
            entity.stateHistory.AddState(newState)
        }

        transactionRepo!!.save(entity)

        if (newState == State.CANCELED) {
            userService!!.cancelationPenalty(userUpdaterEmail)
        }
    }
}