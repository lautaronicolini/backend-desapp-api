package ar.edu.unq.desapp.grupoK.backenddesappapi

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.User
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.errors.UserAlreadyExistException
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.repositories.UserRepository
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.services.UserService
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows

@SpringBootTest
class UserServiceTests {

    @MockK
    var mockUser = mockk<User>()

    @MockK
    var mockUserRepo = mockk<UserRepository>()

    @InjectMockKs
    var userService = UserService()



    @BeforeEach
    fun setUp()  {
        userService.userRepo = mockUserRepo
        MockKAnnotations.init(this) }

    @Test
    fun throwExceptionUserALreadyExistsOnRegistration() {
        var list = ArrayList<User>();
        list.add(mockUser)
        every { mockUser.email } returns "asd@mail.com"
        every { mockUserRepo.findAll() } returns list

        assertThrows<UserAlreadyExistException> {
            userService.createUser(mockUser)
        }
    }

    @Test
    fun findUserByEMailTest() {
        var list = ArrayList<User>();
        list.add(mockUser)
        every { mockUser.email } returns "asd@mail.com"
        every { mockUserRepo.findAll() } returns list

        assert(userService.findUsersByEmail("asd@mail.com").contains(mockUser))
    }

    @Test
    fun userExistTest() {
        var list = ArrayList<User>();
        list.add(mockUser)
        every { mockUser.email } returns "asd@mail.com"
        every { mockUserRepo.findAll() } returns list

        assertTrue(userService.userExists("asd@mail.com"))
        assertFalse(userService.userExists("false@mail.com"))
    }


}