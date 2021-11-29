package ar.edu.unq.desapp.grupoK.backenddesappapi.app.repositories
import ar.edu.unq.desapp.grupoK.backenddesappapi.app.domain.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

//This interface will be autowired in UserServiceImpl for implementing repository methods.
/* CrudRepository parent interface:
-Saves the given entity.
-Returns the entity identified by the given id.
-Returns all entities.
-Returns the number of entities.
-Deletes the given entity.
-Indicates whether an entity with the given id exists.*/

@Repository
interface UserRepository: CrudRepository<User, Long> {

}