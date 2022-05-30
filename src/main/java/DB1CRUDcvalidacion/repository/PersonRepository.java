package DB1CRUDcvalidacion.repository;

import DB1CRUDcvalidacion.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);
}
