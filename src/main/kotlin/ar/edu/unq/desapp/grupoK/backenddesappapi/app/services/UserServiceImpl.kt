package ar.edu.unq.desapp.grupoK.backenddesappapi.app.services

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.User
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.repositories.UserRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.nio.file.Files

@Repository
@Service
class UserServiceImpl{ //TODO calls to userDAO to interact with DB

    @Autowired
    var userRepo: UserRepository? = null

    fun createUser(user: User) {
        userRepo!!.save(user)
    }

    fun userExists(email: String) : Boolean {
        var result =userRepo!!.findAll().filter{user -> user.email==email }[0]

        return result!=null

    }

}