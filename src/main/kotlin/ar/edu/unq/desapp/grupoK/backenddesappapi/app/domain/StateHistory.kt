package ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain

import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.persistence.*

@Entity
class StateHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0
    @ElementCollection
    @OneToMany(cascade=[CascadeType.ALL])
     val stateUpdates: MutableList<StateUpdate> = mutableListOf(StateUpdate(State.NEW))

    fun AddState(newState: State)
    {
        if (ContainsState(newState)) {
            throw Exception("Transaction has been or is already in state $newState")
        } else {
            stateUpdates.add(StateUpdate(newState))
        }
    }

    fun GetStateChangeDate(state:State) :String {
        if (ContainsState(state)) {
            return stateUpdates.first{su -> su.state == state}.updatedOn
        } else {
            throw Exception("Transaction has not been in state $state")
        }
    }

    fun ContainsState(state:State): Boolean {
        return stateUpdates.any{su -> su.state == state}
    }
}

@Entity
class StateUpdate(state: State) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
    val state: State = state
    val updatedOn: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))

    constructor() : this(State.NEW) {}
}