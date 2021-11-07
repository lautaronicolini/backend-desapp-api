package ar.edu.unq.desapp.grupoK.backenddesappapi.app.config

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import java.io.Serializable
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


//This class rejects unauthenticated request and send error code 401
@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint, Serializable {
    @Throws(IOException::class)
    override fun commence(
        request: HttpServletRequest, response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
    }

    companion object {
        private const val serialVersionUID = -7858869558953243875L
    }
}