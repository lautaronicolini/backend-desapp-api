package ar.edu.unq.desapp.grupoK.backenddesappapi.app.services

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.State
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.TransactionActivity
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.User
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.errors.UserAlreadyExistException
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Period

@Repository
@Service
class UserService {

    @Autowired
    var userRepo: UserRepository? = null

    @Autowired
    private val bcryptEncoder: PasswordEncoder? = null

    fun createUser(user: User) {
        if(userExists(user.email)){
            throw UserAlreadyExistException("There is a user already registered with the email ${user.email}")
        }
        user.password = bcryptEncoder!!.encode(user.password)
        userRepo!!.save(user)
    }

    fun getAllUsers(): MutableIterable<User> {
        return userRepo!!.findAll()
    }

    fun userExists(email: String) : Boolean {
        return findUsersByEmail(email).isNotEmpty()
    }

    fun findUsersByEmail(email: String): List<User> {
        return getAllUsers().filter { user -> user.email == email }
    }

    fun updateUsersFromCompletedTransaction(transaction: TransactionActivity) {
        var dateClosed = LocalDateTime.parse(transaction.stateHistory.GetStateChangeDate(State.CLOSED))
        var dateApplied = LocalDateTime.parse(transaction.stateHistory.GetStateChangeDate(State.APPLIED))
        var seller = findUsersByEmail(transaction.sellerEmail!!)[0]
        var buyer = findUsersByEmail(transaction.buyerEmail!!)[0]

        if ((dateClosed.dayOfYear == dateApplied.dayOfYear) && (dateClosed.year == dateApplied.year) && (dateClosed.minute - dateApplied.minute) < 30) {
            seller.reputation = seller.reputation + 10
            buyer.reputation = buyer.reputation + 10
        } else {
            seller.reputation = seller.reputation + 5
            buyer.reputation = buyer.reputation + 5
        }
    }
}