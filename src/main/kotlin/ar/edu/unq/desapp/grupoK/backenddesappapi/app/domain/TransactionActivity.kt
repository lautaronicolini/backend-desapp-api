package ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.dto.CreateTransactionDTO
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.*

@Entity
    class TransactionActivity() {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long = 0
        var time: String = ""
        var crypto: String = ""
        var operationType: OperationType = OperationType.NONE
        var nominalAmount: String = ""
        var unitPriceARS: String = ""
        var buyerEmail: String? = null
        var sellerEmail: String? = null
        @OneToOne(cascade=[CascadeType.ALL])
        var stateHistory: StateHistory = StateHistory()

        constructor(dto:CreateTransactionDTO):this(){
            this.time = getTimeInHours()
            this.crypto = dto.crypto
            this.operationType = dto.operationType
            this.nominalAmount = dto.nominalAmount
            this.unitPriceARS = dto.unitPriceARS
            if (operationType == OperationType.BUY) {
                this.buyerEmail = dto.creatorUser
            } else {
                this.sellerEmail = dto.creatorUser
            }
        }

        @JvmName("getTime1")
        fun getTimeInHours(): String {
            val currentDateTime = LocalDateTime.now()
            return currentDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:SS"))
        }
    }