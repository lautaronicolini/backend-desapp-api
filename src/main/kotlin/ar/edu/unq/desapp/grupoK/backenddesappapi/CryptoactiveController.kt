package ar.edu.unq.desapp.grupoK.backenddesappapi

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController;

@RestController
class CryptoactiveController {
    @RequestMapping("/crypto/prices")
    public  GetAllCryptoactivePrices(){

    }
}