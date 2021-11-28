package ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.dto

class TransactionDetailsDTO(
    transactionId: Int,
    time: String,
    cryptoSymbol: String,
    amount: String,
    cryptoValue: String,
    price: String,
    userName: String,
    userSurname: String,
    userReputation: String,
    operationType: String
) {
    var transactionId = transactionId
    var time = time
    var cryptoSymbol = cryptoSymbol
    var amount = amount
    var cryptoValue = cryptoValue
    var price = price
    var userName = userName
    var userSurname = userSurname
    var userReputation = userReputation
    var operationType = operationType
}