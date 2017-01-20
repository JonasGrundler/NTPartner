package ntpartner.boundary;

import ntpartner.model.Address;
import ntpartner.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PartnerBoundaryService {
	/**
     * Creates a partner
     *
     * @param person create a new partner
     * @return the created partner
     */
    Person createPartner(Person person);

    /**
     * Retrieves all persons in a pageable fashion.
     * 
     * @param pageable parameter for creating pages
     * @return a collection of all persons
     */
    Page<Person> findAllPartner(Pageable pageable);
    
    /**
     * Updates the specified partner
     * 
     * @param person person to update
     */
    Person updatePartner(Person person);
    
    /**
     * Deletes the specified partner
     * 
     * @param person person to delete
     */
    void deletePartner(Person person);
    
    /**
     * Deletes the specified partner (by id)
     * 
     * @param id person to delete
     */
    void deletePartner(Long id);
    
    /**
     * Creates an address
     *
     * @param address create a new address
     * @return the created address
     */
    Address createAddress(Address address);

    /**
     * Retrieves all addresses in a pageable fashion.
     * 
     * @param pageable parameter for creating pages
     * @return a collection of all addresses
     */
    Page<Address> findAllAddresses(Pageable pageable);
    
    /**
     * Updates the specified address
     * 
     * @param address address to update
     */
    Address updateAddress(Address address);
    
    /**
     * Deletes the specified address and deletes the reference in every person (set it to null)
     * 
     * @param address person to address
     */
    void deleteAddress(Address address);
    
    /**
     * Deletes the specified address (by id) and deletes the reference in every person (set it to null)
     * 
     * @param address person to address
     */
    void deleteAddress(Long id);
    

}
