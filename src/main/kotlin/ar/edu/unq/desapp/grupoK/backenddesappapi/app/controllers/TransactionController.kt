package ar.edu.unq.desapp.grupoK.backenddesappapi.app.controllers

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.TransactionActivity
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.dto.CreateTransactionDTO
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.dto.TransactionDetailsDTO
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.services.TransactionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
class TransactionController(private val transactionService: TransactionService) {
    @CrossOrigin
    @RequestMapping("/transaction/details")
    fun GetTransactionDetails(id: Long): ResponseEntity<TransactionDetailsDTO>{
        var transaction =transactionService.GetTransactionDetailsDTOForId(id)
        return ResponseEntity(transaction, HttpStatus.OK)
    }

    @CrossOrigin
    @RequestMapping("/transaction/create")
    fun CreateTransaction(@RequestBody dto:CreateTransactionDTO): ResponseEntity<Any>{
        return ResponseEntity(transactionService.CreateTransaction(dto), HttpStatus.CREATED)
    }

    @CrossOrigin
    @RequestMapping("/transaction/all")
    fun GetAllTransactions(): ResponseEntity<List<TransactionActivity>>{
        var transactions  =transactionService.GetAllTransactions()
        return ResponseEntity(transactions, HttpStatus.OK)
    }

    @CrossOrigin
    @RequestMapping("/transaction/apply")
    fun ApplyToTransactions(id: Long, userEmail: String): ResponseEntity<Any>{
        return ResponseEntity(transactionService.ApplyToTransaction(id, userEmail),HttpStatus.OK)
    }
}