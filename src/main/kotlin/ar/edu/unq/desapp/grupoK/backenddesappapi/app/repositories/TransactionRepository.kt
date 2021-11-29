package ar.edu.unq.desapp.grupoK.backenddesappapi.app.repositories

import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.TransactionActivity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository: CrudRepository<TransactionActivity, Int> {

}