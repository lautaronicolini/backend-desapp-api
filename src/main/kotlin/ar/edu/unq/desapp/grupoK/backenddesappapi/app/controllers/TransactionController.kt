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
    fun GetTransactionDetails(id: Long): ResponseEntity<TransactionDetailsDTO>{
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
        return ResponseEntity(transactionService.GetAllTransactions(), HttpStatus.FOUND)
    }

    @CrossOrigin
    @PostMapping("/apply")
    fun ApplyToTransactions(id: Long, userEmail: String): ResponseEntity<Any>{
        return ResponseEntity(transactionService.ApplyToTransaction(id, userEmail),HttpStatus.OK)
    }

    @CrossOrigin
    @PostMapping("/changeState")
    fun ChangeTransactionState(@RequestBody id: Long, newState: State, userUpdaterEmail: String): ResponseEntity<HttpStatus> {
        transactionService.ChangeTransactionState(id, newState, userUpdaterEmail)
        return ResponseEntity(HttpStatus.OK)
    }
}