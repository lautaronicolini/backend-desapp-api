package ar.edu.unq.desapp.grupoK.backenddesappapi

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.api.controllers.UserController
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.User
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.services.UserService
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeEach

@SpringBootTest
class UserTests {

    @MockK
    var mockUser = mockk<User>()

    @MockK
    var userService = mockk<UserService>()

    @InjectMockKs
    var userController = UserController(userService)

    @BeforeEach
    fun setUp() = MockKAnnotations.init(this)

        @Test
        fun controllerRequestsAllUsers() {
            var list = ArrayList<User>();
            list.add(mockUser)
            every { userService.getAllUsers() } returns list
            assert(userController.getAllUsers().contains(mockUser) )

        }



    }