package ar.edu.unq.desapp.grupoK.backenddesappapi.app.controllers

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.dto.TransactionDetailsDTO
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.services.TransactionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
class TransactionController(private val transactionService: TransactionService) {
    @CrossOrigin
    @PostMapping("/transaction/details")
    fun getTransactionDetails(@RequestBody body:TransactionDetailsDTO): ResponseEntity<Any>{
        //return ResponseEntity(transactionService.GetTransactionWithId(id), HttpStatus.OK)
        var dtoResponse = TransactionDetailsDTO("10/15/2021 14:45:00", "ATOMUSDT", "100", "", "3741.521", "Jorge", "Perez", "50")

        transactionService.CreateTransaction()

        return ResponseEntity(dtoResponse, HttpStatus.OK)
    }
}