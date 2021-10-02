package ar.edu.unq.desapp.grupoK.backenddesappapi.app.services

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.CryptoActive
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import org.springframework.core.io.ClassPathResource
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.nio.file.Files

class CryptoActivePricesService {
    fun GetAllCryptoActivePrices(): List<CryptoActive>{
        var result = mutableListOf<CryptoActive>()
        var cryptoActivesFile = ClassPathResource("CryptoActives.json").file

        val restTemplate:RestTemplate = RestTemplate()

        val cryptoActiveList:JsonArray = Json.parseToJsonElement((String(Files.readAllBytes(cryptoActivesFile.toPath())))).jsonArray
        for (ca in cryptoActiveList){
            result.add(restTemplate.getForObject<CryptoActive>("https://api1.binance.com/api/v3/ticker/price?symbol=$ca"))
        }

        return result
    }
}