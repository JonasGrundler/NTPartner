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
    List<Person> findByNameFirstname(String firstname);
    List<Person> findByNameLastname(String lastname);
    List<Person> findByNameFirstnameAndNameLastname(String firstname, String lastname);
    
    @Query("SELECT p FROM Person p WHERE p.address = :idAddress")
    public List<Person> findByAddress(@Param("idAddress") Long idAddress);
}
