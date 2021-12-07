package ar.edu.unq.desapp.grupoK.backenddesappapi.app.controllers

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.State
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.TransactionActivity
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.dto.CreateTransactionDTO
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.dto.TransactionDetailsDTO
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.services.TransactionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("transaction")
class TransactionController(private val transactionService: TransactionService) {
    @CrossOrigin
    @GetMapping("/details")
    fun getTransactionDetails(id: Int): ResponseEntity<TransactionDetailsDTO>{
        return try {
            ResponseEntity(transactionService.GetTransactionDetailsDTOForId(id), HttpStatus.OK)
        }
        catch(e: Exception) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @CrossOrigin
    @PostMapping("/create")
    fun createTransaction(@RequestBody dto:CreateTransactionDTO): ResponseEntity<Any>{
        return try {
            ResponseEntity(transactionService.CreateTransaction(dto), HttpStatus.CREATED)
        }
        catch (e: Exception) {
            ResponseEntity.badRequest().body("Transaction couldn't be created")
        }
    }

    @CrossOrigin
    @GetMapping("/all")
    fun getAllTransactions(): ResponseEntity<List<TransactionActivity>>{
        return ResponseEntity(transactionService.GetAllTransactions(), HttpStatus.OK)
    }

    @CrossOrigin
    @GetMapping("/apply")
    fun applyToTransactions(id: Int, userEmail: String): ResponseEntity<Any>{
        return try {
            ResponseEntity(transactionService.ApplyToTransaction(id, userEmail),HttpStatus.OK)
        }
        catch (e:Exception) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @CrossOrigin
    @PostMapping("/changeState")
    fun changeTransactionState(id: Int, newState: String, userUpdaterEmail: String): ResponseEntity<Any> {
        try {
            var state : State? = null
            if (newState=="APPLIED"){state= State.APPLIED}
            if (newState=="TRANSFERENCE_DONE"){state= State.TRANSFERENCE_DONE}
            if (newState=="CLOSED"){state=State.CLOSED}
            if (newState=="CANCELED"){state=State.CANCELED}
            transactionService.ChangeTransactionState(id, state!!, userUpdaterEmail)
            return ResponseEntity(HttpStatus.OK)
        } catch (e:Exception) {
            return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }
}