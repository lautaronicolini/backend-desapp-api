package ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain

import kotlinx.serialization.Serializable
import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Serializable //this class can be converted to JSON
@Entity
@Table(name = "User")
 class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
  // @Column(name = "user_id")
   val id : Int = 0;

   @NotNull
   // @NotBlank(message = "Name is mandatory")
   var name: String = "";

   @NotNull
   // @NotBlank(message = "Last Name is mandatory")
   var lastName: String = "";

   @NotNull
   //@NotBlank(message = "Address is mandatory")
   var address: String = "";

   @NotNull
   //@NotBlank(message = "Email is mandatory")
   var email: String = "";

   @NotNull
   //@NotBlank(message = "Password is mandatory")
   var password: String = "";

   @NotNull
   //@NotBlank(CVU = "Name is mandatory")
   var cvu: String = "";

   @NotNull
   //@NotBlank(message = "Wallet Address is mandatory")
   var walletAddress: String = ""

   @NotNull
   //@NotBlank(message = "Wallet Address is mandatory")
   var reputation: Int = 0
}