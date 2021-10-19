package ar.edu.unq.desapp.grupoK.backenddesappapi.app.api.controllers

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.api.TokenJWT
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.api.dto.RegisterDto
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.controllers.AuthController
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.User
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.dto.LoginDto
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.jwt.JwtRequest
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@CrossOrigin(origins = ["http://localhost:3000"])  //enables CORS for this controller. It allows requests from a diff origin
@RestController
@RequestMapping("api")
class UserController (private val userService : UserService) {

    //val back CriptoP2P = getCriptoP2P();
    //val  userCont =  UserController();
    //val jwt = TokenJWT();
    val jwtAuth = AuthController()

    @GetMapping("/hello")
    open fun hello(): ResponseEntity<String?>? {
        return ResponseEntity("Hello World!", HttpStatus.OK)
    }

    @CrossOrigin
    @PostMapping("/register")
    fun registerUser( @RequestBody body: RegisterDto): ResponseEntity<String> {
        // persisting the user
        var userJSON : String;
        try {
            val user = User()
          user.name = body.name
          user.lastName = body.lastName
          user.address = body.address
          user.email = body.email
          user.password = body.password
          user.cvu = body.cvu
          user.walletAddress = body.walletAddress

          userJSON = userService.createUser(user)
      }catch (e :Exception){
          return  ResponseEntity.badRequest().body("user could not be registered: ${e.message}")
      }
        return ResponseEntity.ok(userJSON)
    }

    @PostMapping("/login")
    fun login( @RequestBody body: LoginDto, response : HttpServletResponse): ResponseEntity<Any> {
        val user = userService.getUser(body.email)
        return if (user!=null){
            if(user.password !=body.password) {
                ResponseEntity.badRequest().body("Incorrect password")
            }
            else{
                //val token = jwt.generateToken(user)
                //val cookie = Cookie("jwt",token)
                //cookie.isHttpOnly=true // to assure the frontend cannot access the cookie
                //response.addCookie(cookie
                val autReq = JwtRequest(user.email, user.password)
                jwtAuth.createAuthenticationToken(autReq)
                ResponseEntity.ok("success")
            }
        } else{
            ResponseEntity.badRequest().body("Cannot find user")
        }
    }








   /* @PostMapping("/login")
    fun login( @RequestBody body: LoginDto, response : HttpServletResponse): ResponseEntity<Any> {
        val user = userService.getUser(body.email)
        return if (user!=null){
            if(user.password !=body.password) {
                ResponseEntity.badRequest().body("Incorrect password")
            }
            else{
                //val token = jwt.generateToken(user)
                //val cookie = Cookie("jwt",token)
                //cookie.isHttpOnly=true // to assure the frontend cannot access the cookie
                //response.addCookie(cookie)

                ResponseEntity.ok("success")
            }
        } else{
            ResponseEntity.badRequest().body("Cannot find user")
        }
    }
*/
}