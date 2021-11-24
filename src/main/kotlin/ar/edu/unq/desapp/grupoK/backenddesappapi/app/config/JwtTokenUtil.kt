package ar.edu.unq.desapp.grupoK.backenddesappapi.app.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.io.Serializable
import java.security.Key
import java.util.*
import java.util.function.Function
import javax.crypto.Cipher.SECRET_KEY
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter


//This class defines the methods to generate/validate tokens
@Component
class JwtTokenUtil : Serializable {
    @Value("")
    private val secret: ByteArray = "123456".toByteArray()
    fun getUsernameFromToken(token: String?): String {
        return getClaimFromToken(token) { obj: Claims -> obj.subject }
    }

    fun getIssuedAtDateFromToken(token: String?): Date {
        return getClaimFromToken(
            token
        ) { obj: Claims -> obj.issuedAt }
    }

    fun getExpirationDateFromToken(token: String?): Date {
        return getClaimFromToken(
            token
        ) { obj: Claims -> obj.expiration }
    }

    fun <T> getClaimFromToken(token: String?, claimsResolver: Function<Claims, T>): T {
        val claims = getAllClaimsFromToken(token)
        return claimsResolver.apply(claims)
    }

    private fun getAllClaimsFromToken(token: String?): Claims {
        return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).body
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    private fun ignoreTokenExpiration(token: String): Boolean {
        // here you specify tokens, for that the expiration is ignored
        return false
    }

    fun generateToken(userDetails: UserDetails): String {
        val claims: Map<String, Any> = HashMap()
        return doGenerateToken(claims, userDetails.getUsername())
    }

    private fun doGenerateToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
            .signWith(SignatureAlgorithm.HS512, signingKey).compact()
    }

    fun canTokenBeRefreshed(token: String): Boolean {
        return !isTokenExpired(token) || ignoreTokenExpiration(token)
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = getUsernameFromToken(token)
        return username == userDetails.getUsername() && !isTokenExpired(token)
    }

    companion object {
        private const val serialVersionUID = -2550185165626007488L
        const val JWT_TOKEN_VALIDITY = (5 * 60 * 60).toLong()
        const val SECRET_KEY = "123456"
        val signingKey: Key = SecretKeySpec(DatatypeConverter.parseBase64Binary(SECRET_KEY), SignatureAlgorithm.HS512.jcaName)

    }
}