package ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.dto

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.OperationType

class CreateTransactionDTO ()
{
    var crypto:String = ""
    var operationType:OperationType = OperationType.NONE
    var nominalAmount:String = ""
    var unitPriceARS:String = ""
    var creatorUser:String = ""
}