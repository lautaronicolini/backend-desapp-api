package ar.edu.unq.desapp.grupoK.backenddesappapi.app.services

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.User
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Files

@Repository
@Service
class UserServiceImpl{ //TODO calls to userDAO to interact with DB

    /*@Autowired
    private var userDAO : UserDAO;
    */


    final var resource = ClassPathResource("dummyDB.json").file
    var users = String(Files.readAllBytes(resource.toPath()))

     fun createUser(user: User) {
        //dao.save(user)
           val newUser = Json.encodeToString(user)
           val builder = StringBuilder(users)
           builder.insert(users.length-1,", $newUser")
            print(builder.toString())
           File("C:\\Users\\Camila\\Desktop\\backend-desapp-api\\src\\main\\resources\\dummyDB.json").writeText(builder.toString())

    }

    fun userExists(email: String) : Boolean {
        //val exists=//dao.find()
        var result =false
        val userList: JsonArray = Json.parseToJsonElement((String(Files.readAllBytes(resource.toPath())))).jsonArray
        return result

    }

}