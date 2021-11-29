package ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.security

import java.io.Serializable


class JwtResponse(val token: String) : Serializable {

    companion object {
        private const val serialVersionUID = -8091879091924046844L
    }
}