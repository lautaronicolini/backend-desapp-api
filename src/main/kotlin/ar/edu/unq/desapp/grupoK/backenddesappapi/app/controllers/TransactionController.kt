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
        return ResponseEntity(transactionService.GetTransactionDetailsDTOForId(id), HttpStatus.OK)
    }

    @CrossOrigin
    @RequestMapping("/transaction/create")
    fun CreateTransaction(@RequestBody dto:CreateTransactionDTO): ResponseEntity<HttpStatus>{
        transactionService.CreateTransaction(dto)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @CrossOrigin
    @RequestMapping("/transaction/all")
    fun GetAllTransactions(): ResponseEntity<List<TransactionActivity>>{
        return ResponseEntity(transactionService.GetAllTransactions(), HttpStatus.FOUND)
    }

    @CrossOrigin
    @RequestMapping("/transaction/apply")
    fun ApplyToTransactions(id: Long, userEmail: String): ResponseEntity<HttpStatus>{
        transactionService.ApplyToTransaction(id, userEmail)
        return ResponseEntity(HttpStatus.OK)
    }
}