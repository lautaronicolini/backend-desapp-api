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
        if (transactionRepo!!.existsById(id)) {
            return transactionRepo!!.findById(id).get()
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
        return TransactionDetailsDTO(entity.time, entity.crypto, entity.nominalAmount, entity.unitPriceARS, price.toString(), creatorUser.name, creatorUser.lastName, creatorUser.reputation.toString())
    }

    fun GetAllTransactions():List<TransactionActivity>{
        return transactionRepo!!.findAll().toList()
    }

    fun ApplyToTransaction(id: Long, userEmail: String){
        var entity = GetTransactionWithId(id)
        if (entity.operationType == OperationType.BUY) {
            entity.sellerEmail = userEmail
        } else {
            entity.buyerEmail = userEmail
        }
        entity.state = State.APPLIED
        transactionRepo!!.save(entity)
    }
}