package ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain

import kotlinx.serialization.Serializable
import org.jetbrains.annotations.NotNull
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Serializable //this class can be converted to JSON
@Entity
//@Table(name = "user")
 class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
  // @Column(name = "user_id")
   val id : Int = 0;

   @NotNull
  // @Column(name ="name")
   // @NotBlank(message = "Name is mandatory")
   var name: String = "";

   @NotNull
   //@Column(name ="lastName")
   // @NotBlank(message = "Last Name is mandatory")
   var lastName: String = "";

   @NotNull
  // @Column(name ="address")
   //@NotBlank(message = "Address is mandatory")
   var address: String = "";

   @NotNull
   //@Column(name ="email")
   //@NotBlank(message = "Email is mandatory")
   var email: String = "";

   @NotNull
   //@Column(name ="password")
   //@NotBlank(message = "Password is mandatory")
   var password: String = "";

   @NotNull
   //@Column(name ="CVU")
   //@NotBlank(CVU = "Name is mandatory")
   var CVU: String = "";

   @NotNull
   //@Column(name ="walletAddress")
   //@NotBlank(message = "Wallet Address is mandatory")
   var walletAddress: String = ""
}