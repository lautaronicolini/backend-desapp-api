package ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.security

import java.io.Serializable


class JwtRequest : Serializable {

    private var username: String? = null
    private var password: String? = null

    //default constructor for JSON Parsing
    constructor() {}
    constructor(username: String?, password: String?) {
        this.username = username
        this.password = password
    }
    fun getUsername(): String {
    return this.username!!
    }

    fun getPassword(): String {
    return this.password!!
    }


    companion object {
        private const val serialVersionUID = 5926468583005150707L
    }
}