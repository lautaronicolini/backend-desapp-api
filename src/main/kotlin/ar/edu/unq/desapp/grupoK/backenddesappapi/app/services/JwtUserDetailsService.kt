package ar.edu.unq.desapp.grupoK.backenddesappapi.app.services

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.User as DomUser


//UserDetailsService interface is used in order to search the username, password and GrantedAuthorities for given user.
@Service
class JwtUserDetailsService : UserDetailsService {
    @Autowired
    private var userRepository: UserRepository? = null



    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val user: DomUser? = userRepository!!.findByEmail(email)
        if (user == null) {
            throw UsernameNotFoundException("User not found with email: $email");
        }
            return User(user.email, user.password,ArrayList<GrantedAuthority>());
        }

}