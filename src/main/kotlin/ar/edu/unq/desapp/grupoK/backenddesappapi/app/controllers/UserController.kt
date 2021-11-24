package ar.edu.unq.desapp.grupoK.backenddesappapi.app.api.controllers

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.api.dto.RegisterDTO
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.config.JwtTokenUtil
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.controllers.JwtAuthenticationController
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.User
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.security.JwtRequest
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.security.JwtResponse
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.services.JwtUserDetailsService
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate


@CrossOrigin(origins = ["http://localhost:3000"])  //enables CORS for this controller. It allows requests from a diff origin
@RestController

class UserController (private val userService : UserService) {
    @Autowired
    private val jwtTokenUtil: JwtTokenUtil? = null
    @Autowired
    private val userDetailsService: JwtUserDetailsService? = null
    @Autowired
    private val authenticationManager: AuthenticationManager? = null
    val authController : JwtAuthenticationController? = null

    @GetMapping("/hello")
     fun hello(): ResponseEntity<String?>? {
        return ResponseEntity("Hello World!", HttpStatus.OK)
    }

    @CrossOrigin
    @PostMapping("/login")
    fun login( @RequestBody body: JwtRequest): ResponseEntity<String>? {
        authenticate(body.getUsername(), body.getPassword())
        val userDetails = userDetailsService!!.loadUserByUsername(body.getUsername())
        val token = jwtTokenUtil!!.generateToken(userDetails)
        return ResponseEntity.ok(JwtResponse(token).toString())
        //var response :JwtResponse  = JwtResponse(authController!!.createAuthenticationToken(body).toString())
    }

    @CrossOrigin(origins = ["http://localhost:3000"])
    @PostMapping("/register")
    fun registerUser( @RequestBody body: RegisterDTO): ResponseEntity<String> {
        // persisting the user
      try {
          val user = User()
          user.name = body.name
          user.lastName = body.lastName
          user.address = body.address
          user.email = body.email
          user.password = body.password
          user.cvu = body.cvu
          user.walletAddress = body.walletAddress

          userService.createUser(user)
      } catch (e :Exception){
          return  ResponseEntity.badRequest().body("user could not be registered: ${e.message}")
      }
        return ResponseEntity.ok("User was registered")
    }

    @GetMapping("/users")
    fun getAllUsers(): MutableIterable<User> {
        return userService.getAllUsers()
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