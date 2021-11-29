package ar.edu.unq.desapp.grupoK.backenddesappapi.app.errors

class UserAlreadyExistException : Exception {
    constructor(): super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}
