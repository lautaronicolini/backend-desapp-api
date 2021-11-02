package ar.edu.unq.desapp.grupoK.backenddesappapi.app.controllers

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.dto.TransactionDetailsDTO
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.services.TransactionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
class TransactionController(private val transactionService: TransactionService) {
    @CrossOrigin
    @RequestMapping("/transaction/details")
    fun GetTransactionDetails(id: Long): ResponseEntity<TransactionDetailsDTO>{
        //return ResponseEntity(transactionService.GetTransactionWithId(id), HttpStatus.OK)
        var dtoResponse = TransactionDetailsDTO("10/15/2021 14:45:00", "ATOMUSDT", "100", "", "3741.521", "Jorge", "Perez", "50")
        return ResponseEntity(dtoResponse, HttpStatus.OK)
    }
}