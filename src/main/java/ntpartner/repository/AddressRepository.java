package ntpartner.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import ntpartner.model.Address;

/**
 * JPA Repository for accessing Address entities
 */
@Transactional
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByCity(String city);
}
