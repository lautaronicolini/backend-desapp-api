package ar.edu.unq.desapp.grupoK.backenddesappapi.app.services

class TransactionService {
//transaccion es a nivel servicio (begin commit -
    //un thread en java es un objeto - mapas globales k=hread v=datos
    //guardo el obj transaccion en mapa global donde la key es el thread q da servicios a request
    //spring tiene asociado thread y variables, una variable es la transaccion actual - se puede pedir trans actual abierta - se busca y se hace commit
    //transactional

}