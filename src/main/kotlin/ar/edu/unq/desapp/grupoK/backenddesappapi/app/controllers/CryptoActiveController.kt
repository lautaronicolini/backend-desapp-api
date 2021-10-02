package ar.edu.unq.desapp.grupoK.backenddesappapi.app.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.CryptoActive
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.services.CryptoActivePricesService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@RestController
class CryptoActiveController {
    @RequestMapping("/crypto/prices")
    fun GetAllCryptoActivePrices(): ResponseEntity<List<CryptoActive>> {

        val result = CryptoActivePricesService().GetAllCryptoActivePrices()
        return ResponseEntity(result, HttpStatus.OK)
    }
}