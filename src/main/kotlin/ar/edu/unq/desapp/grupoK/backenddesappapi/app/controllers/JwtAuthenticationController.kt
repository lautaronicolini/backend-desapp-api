package ar.edu.unq.desapp.grupoK.backenddesappapi.app.controllers

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.config.JwtTokenUtil
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.security.JwtRequest
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.security.JwtResponse
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.services.JwtUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:3000"])  //enables CORS for this controller. It allows requests from a diff origin
@RestController
class JwtAuthenticationController {

    @Autowired
    private val authenticationManager: AuthenticationManager? = null

    @Autowired
    private val jwtTokenUtil: JwtTokenUtil? = null

    @Autowired
    private val userDetailsService: JwtUserDetailsService? = null

    @CrossOrigin
    @RequestMapping(value = ["/authenticate"], method = [RequestMethod.POST])
    @Throws(java.lang.Exception::class)
    fun createAuthenticationToken(@RequestBody authenticationRequest: JwtRequest): ResponseEntity<*>? {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        val userDetails = userDetailsService!!.loadUserByUsername(authenticationRequest.getUsername())
        val token = jwtTokenUtil!!.generateToken(userDetails)
        return ResponseEntity.ok(JwtResponse(token))
    }

    @Throws(Exception::class)
    fun authenticate(username: String, password: String) {
        try {
            authenticationManager!!.authenticate(UsernamePasswordAuthenticationToken(username, password))
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS", e)
        }
    }
}