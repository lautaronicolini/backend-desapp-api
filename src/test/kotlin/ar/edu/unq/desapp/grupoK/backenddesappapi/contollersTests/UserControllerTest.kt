package ar.edu.unq.desapp.grupoK.backenddesappapi.contollersTests

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.api.controllers.UserController
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.User
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.services.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@WebMvcTest(controllers = [UserController::class])
@AutoConfigureMockMvc
class UserTests() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userService : UserService;


        @Test
        fun whenUserIsRegisteredShouldReturnA200Status() {

            lateinit var user : User;
            var response = {
                var status = 200
                var body = "user was registered"
            }
            `when`(userService.createUser(user)).thenReturn(response)
            mockMvc.perform(MockMvcRequestBuilders.post("/api/register/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)

        }
    fun asJsonString(obj: Any?): String? {
        return try {
            val mapper = ObjectMapper()
            mapper.writeValueAsString(obj)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}

private fun Any.thenReturn(response: () -> Unit) {

}
