package ar.edu.unq.desapp.grupoK.backenddesappapi.app.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.CryptoActive
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.services.CryptoActivePricesService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("cryptoActives")
class CryptoActiveController {
    @CrossOrigin
    @RequestMapping("/crypto/prices")
    fun GetAllCryptoActivePrices(): ResponseEntity<List<CryptoActive>> {
        val result = CryptoActivePricesService().GetAllCryptoActivePrices()
        return ResponseEntity(result, HttpStatus.OK)
    }
}