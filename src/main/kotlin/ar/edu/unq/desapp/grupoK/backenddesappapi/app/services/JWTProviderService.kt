package ar.edu.unq.desapp.grupoK.backenddesappapi.app.services

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate


open class JWTProviderService {

    @Value("\${auth0.audience}")
    private val audience: String = String()

    fun GetJWT() : String {
        val url = "https://${audience}/oauth/token"
        val body = CreateBody()
        val restTemplate: RestTemplate = RestTemplate()
        val response: ResponseEntity<Void> = restTemplate.postForEntity<Void>(url, body, Void::class.java)
        return response.body.toString()
    }

    private fun CreateBody():MutableMap<String, String>{
        val map: MutableMap<String, String> = HashMap()
        map["client_id"] = "kWfQL8zH4n62yFpaDtq4gkAx6sWpTfQI"
        map["client_secret"] = "_PnML8GvYkwHbuuLoAHYzbub6A81ONyo3ObzmzjrFucsYi8gzfbAAOsIwuZyIRfY"
        map["audience"] = audience
        return map
    }
}