package ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain

import org.springframework.stereotype.Component

@Component
class CriptoP2P(
        private val users: MutableList<User> = mutableListOf()
) {

    fun addUser(user: User) {
        users.add(user);
    }
    fun deleteUser(user: User) {
        users.remove(user);
    }

}