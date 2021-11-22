package ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain

import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAmount
import java.util.*
import javax.persistence.*

@Entity
    class TransactionActivity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Int = 0;

        var time: String = getTimeInHours();

        var currency: String = "";

        var amountPesos: Double = 0.0; //in Argentine pesos

        var currencyValue: Double = 0.0;

         var userName: String = ""

         var reputation: Int = 0

         var transactions: Int = 0


        fun TransactionActivity(currency: String, amountPesos: Double, currencyValue: Double, userName:String,userLastName:String, reputation:Int, transactions:Int){
            this.currency = currency;
            this.amountPesos = amountPesos;
            this.currencyValue = currencyValue;
            this.userName = "$userName $userLastName";
            this.reputation = reputation;
            this.transactions = transactions;
        }


        @JvmName("getTime1")
        fun getTimeInHours(): String {
            val currentDateTime = LocalDateTime.now()
            return currentDateTime.format(DateTimeFormatter.ofPattern("HH"))
        }

    }
