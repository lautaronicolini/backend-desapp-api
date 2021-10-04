package ar.edu.unq.desapp.grupoK.backenddesappapi.app.services

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.CryptoActive
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import org.springframework.core.io.ClassPathResource
import org.springframework.scheduling.annotation.Async
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.nio.file.Files
import khttp.get
import khttp.responses.Response

class CryptoActivePricesService {
    fun GetAllCryptoActivePrices(): List<CryptoActive>{
        var result = mutableListOf<CryptoActive>()
        var cryptoActivesFile = ClassPathResource("CryptoActives.json").file
        var aux:CryptoActive


        val exchangeRate:Float = GetUSDARSExchange()
        val cryptoActiveList:JsonArray = Json.parseToJsonElement((String(Files.readAllBytes(cryptoActivesFile.toPath())))).jsonArray
        for (ca in cryptoActiveList){
            aux = GetUSDPriceForCrypto(ca.toString())
            //aux.price = (aux.price.toFloat() * exchangeRate).toString()
            result.add(aux)
        }

        return result
    }

    fun GetUSDARSExchange():Float{
        var result: Response = get("https://api.estadisticasbcra.com/usd_of", headers = mapOf("Authorization" to "BEARER eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NjQ5MTYwNDgsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJsYXV0YXJvbm5pY29saW5pQGdtYWlsLmNvbSJ9.XnRFzJBbGU2vZTzgkokhPPQTL2fQxwcLy2Cui3QAcH09B-YIcbEh5Vi1oX0Zu5OGw-1w-eOH0OkU61leqrFsUQ"))
        result.request
        return 1.toFloat()
    }

    @Async
    open fun GetUSDPriceForCrypto(crypto:String):CryptoActive{
        val restTemplate:RestTemplate = RestTemplate()
        val result = restTemplate. .getForObject<CryptoActive>("https://api1.binance.com/api/v3/ticker/price?symbol=$crypto")
        return result
    }
}