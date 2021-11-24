package ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain

import kotlinx.serialization.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Serializable
@Entity
class CryptoActive{
    @Id
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    var symbol:String = ""
    var price:String = ""
}

