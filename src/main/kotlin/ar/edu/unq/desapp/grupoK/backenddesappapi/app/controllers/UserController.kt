package ar.edu.unq.desapp.grupoK.backenddesappapi.app.api.controllers

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.api.dto.RegisterData
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.User
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.services.UserServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api")

class UserController (private val userService : UserServiceImpl) {

    //val back CriptoP2P = getCriptoP2P();
    //val  userCont =  UserController();

    @GetMapping("/hello")
    open fun hello(): ResponseEntity<String?>? {
        return ResponseEntity("Hello World!", HttpStatus.OK)
    }

    @PostMapping("/register")
    fun registerUser( @RequestBody body: RegisterData): ResponseEntity<String> {
        // persisting the user
      try {
          val user = User()
          user.name = body.name
          user.lastName = body.lastName
          user.address = body.address
          user.email = body.email
          user.password = body.password
          user.CVU = body.CVU
          user.walletAddress = body.walletAddress

          userService.createUser(user)
      }catch (e :Exception){
          return  ResponseEntity.badRequest().body("user could not be registered")
      }
        return ResponseEntity.ok("User was registered")
    }


}