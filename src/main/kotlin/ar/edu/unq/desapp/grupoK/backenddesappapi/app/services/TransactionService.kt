package ar.edu.unq.desapp.grupoK.backenddesappapi.app.services

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.TransactionActivity
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.repositories.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Repository
@Service
class TransactionService {
    @Autowired
    private var transactionRepo: TransactionRepository? = null

    fun CreateTransaction(): Boolean {
        transactionRepo!!.save(TransactionActivity())
        return true
    }

    fun GetTransactionWithId(id:Long):TransactionActivity {
        return transactionRepo!!.findById(id).get()
    }
}