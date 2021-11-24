package ar.edu.unq.desapp.grupoK.backenddesappapi.app.services

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.CryptoActive
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.Price
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.dto.PriceDTO
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import org.springframework.cache.annotation.Cacheable
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.nio.file.Files
import java.util.*

@Service
 class CryptoActivePricesService {

    @Cacheable(value = ["cryptoactives"])
    fun GetAllCryptoActivePrices(): List<CryptoActive>{
        var result = mutableListOf<CryptoActive>()
        var cryptoActivesFile = ClassPathResource("CryptoActives.json").file
        var aux:CryptoActive

        val exchangeRate:Float = GetUSDARSExchange()
        val cryptoActiveList:JsonArray = Json.parseToJsonElement((String(Files.readAllBytes(cryptoActivesFile.toPath())))).jsonArray
        for (ca in cryptoActiveList){
            aux = GetUSDPriceForCrypto(ca.toString())
            aux.price = (aux.price.toFloat() * exchangeRate).toString()
            result.add(aux)
        }

        return result
    }

    @Cacheable(value = ["ars"])
    fun GetUSDARSExchange():Float{
        val url = "https://www.dolarsi.com/api/api.php?type=valoresprincipales"

        val restTemplate:RestTemplate = RestTemplate()
        val response = restTemplate.exchange(url, HttpMethod.GET, null, Array<PriceDTO>::class.java)
        val result:Price = response.body!!.first()!!.casa!!
        return result.compra.replace(',','.').toFloat()
    }

    @Cacheable(value = ["usd"])
     fun GetUSDPriceForCrypto(crypto:String):CryptoActive{
        val restTemplate:RestTemplate = RestTemplate()
        val result = restTemplate.getForObject<CryptoActive>("https://api1.binance.com/api/v3/ticker/price?symbol=$crypto")
        return result
    }
}