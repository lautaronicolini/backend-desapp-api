package ar.edu.unq.desapp.grupoK.backenddesappapi.app.api
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
@Component
class TokenJWT {

    private val provider = UserTokenGenerator()

    fun generateToken(user: User): String {
        return provider.generate(user)
    }

    fun validate(token: String): String ?{
       // val token = provider.validateToken(token)
       // if (!token.isPresent) throw NotFoundToken()
        //return token.get().getClaim("id").asString()
        return null
    }

}

class UserTokenGenerator  {
    fun generate(user: User): String {
        val issuer = user.email

        return Jwts.builder()
            .setIssuer(issuer)
            .signWith(SignatureAlgorithm.ES256, "SuperSecretKey")
            .compact()
    }
}