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
    fun GetTransactionDetails(id: Int): ResponseEntity<TransactionDetailsDTO>{
        return ResponseEntity(transactionService.GetTransactionDetailsDTOForId(id), HttpStatus.OK)
    }

    @CrossOrigin
    @PostMapping("/create")
    fun CreateTransaction(@RequestBody dto:CreateTransactionDTO): ResponseEntity<Any>{
        return ResponseEntity(transactionService.CreateTransaction(dto), HttpStatus.CREATED)
    }

    @CrossOrigin
    @GetMapping("/all")
    fun GetAllTransactions(): ResponseEntity<List<TransactionActivity>>{
        return ResponseEntity(transactionService.GetAllTransactions(), HttpStatus.OK)
    }

    @CrossOrigin
    @GetMapping("/apply")
    fun ApplyToTransactions(id: Int, userEmail: String): ResponseEntity<Any>{
        return ResponseEntity(transactionService.ApplyToTransaction(id, userEmail),HttpStatus.OK)
    }

    @CrossOrigin
    @PostMapping("/changeState")
    fun ChangeTransactionState(@RequestBody id: Int, newState: String, userUpdaterEmail: String ): ResponseEntity<HttpStatus> {
        var state : State? = null
        if (newState=="APPLIED"){state= State.APPLIED}
        if (newState=="TRANSFERENCE_DONE"){state= State.TRANSFERENCE_DONE}
        if (newState=="CLOSED"){state=State.CLOSED}
        if (newState=="CANCELED"){state=State.CANCELED}
            transactionService.ChangeTransactionState(id, state!!, userUpdaterEmail)
        return ResponseEntity(HttpStatus.OK)
    }
}