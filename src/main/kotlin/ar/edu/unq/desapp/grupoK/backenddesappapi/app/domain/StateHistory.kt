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
        if (stateUpdates.any{su -> su.state == newState}) {
            throw Exception("Transaction has been or is already in state $newState")
        } else {
            stateUpdates.add(StateUpdate(newState))
        }
    }

    fun GetStateChangeDate(state:State) :String {
        if (stateUpdates.any{su -> su.state == state}) {
            return stateUpdates.first{su -> su.state == state}.updatedOn
        } else {
            throw Exception("Transaction has not been in state $state")
        }
    }
}

@Entity
class StateUpdate(state: State) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
    val state: State = state
    val updatedOn: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:SS"))

    constructor() : this(State.NEW) {}
}