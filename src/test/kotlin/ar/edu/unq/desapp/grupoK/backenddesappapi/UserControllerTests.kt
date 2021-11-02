package ar.edu.unq.desapp.grupoK.backenddesappapi

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.api.controllers.UserController
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.api.dto.RegisterDto
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.User
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.errors.UserAlreadyExistException
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.services.UserService
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.BeforeEach
import org.springframework.http.HttpStatus.

@SpringBootTest
class UserControllerTests {

    @MockK
    var mockUser = mockk<User>()

    @MockK
    var userService = mockk<UserService>()

    @MockK
    var mockUserDto = mockk<RegisterDto>()

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

    @Test
    fun whenRegisterUserFailsGetBadRequestResponse() {
        every { userService.createUser(mockUser) } throws UserAlreadyExistException()

        userController.registerUser(mockUserDto).body?.let { assert(it.contains("user could not be registered:")) }
        assert(userController.registerUser(mockUserDto).statusCode == HttpStatus.BAD_REQUEST)
    }

    @Test
    fun throwExceptionWhenServiceFails() {

             every { userService.createUser(mockUser) } returns (throw UserAlreadyExistException())
             userController.registerUser(mockUserDto) }

/*
    @Test
    fun whenRegisterUserSucceedsGetOkResponse() {
        every {mockUserDto.email} returns "asd@asd";
        every { mockUserDto.cvu } returns "1231231231231231231231";
        every {mockUserDto.address } returns "123";
        every {mockUserDto.name } returns "Pepe";
        every {mockUserDto.lastName } returns "Asd";
        every { mockUserDto.password } returns "passs";
        every {mockUserDto.walletAddress} returns "123123123123123";

        userController.registerUser(mockUserDto).body?.let { assert(it.contains("User was registered")) }
    }
*/

}