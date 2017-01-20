package ntpartner.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ntpartner.model.Person;

/**
 * JPA Repository for accessing Person entities
 */
@Transactional
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByFirstname(String firstname);
    List<Person> findByLastname(String lastname);
    List<Person> findByFirstnameAndLastname(String firstname, String lastname);
    
    @Query("SELECT p FROM Person p WHERE p.idAddress = :idAddress")
    public List<Person> findByAddress(@Param("idAddress") Long idAddress);
}
