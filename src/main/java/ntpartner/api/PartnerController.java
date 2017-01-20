package ntpartner.api;

import java.util.List;

import javax.transaction.Transactional;

import ntpartner.boundary.PartnerBoundaryService;
import ntpartner.model.Address;
import ntpartner.model.Person;
import ntpartner.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-Service to access Partner resources
 */
@RestController
@Transactional
public class PartnerController {
	public static final String PERSON_RESOURCE_PATH = "/educama/v1/person";

    @Autowired
    private PartnerBoundaryService partnerService;
 
//    /**
//     * Creates a partner
//     *
//     * @param person create a new partner
//     * @return the created partner
//     */
//    Person createPartner(Person person);
//
//    /**
//     * Retrieves all persons in a pageable fashion.
//     * 
//     * @param pageable parameter for creating pages
//     * @return a collection of all persons
//     */
//    Page<Person> findAllPartner(Pageable pageable);
//    
//    /**
//     * Updates the specified partner
//     * 
//     * @param person person to update
//     */
//    Person updatePartner(Person person);
//    
//    /**
//     * Deletes the specified partner
//     * 
//     * @param person person to delete
//     */
//    void deletePartner(Person person);
//    
//    /**
//     * Deletes the specified partner (by id)
//     * 
//     * @param id person to delete
//     */
//    void deletePartner(Long id);
//    
//    /**
//     * Creates an address
//     *
//     * @param address create a new address
//     * @return the created address
//     */
//    Address createAdress(Address address);
//
//    /**
//     * Retrieves all addresses in a pageable fashion.
//     * 
//     * @param pageable parameter for creating pages
//     * @return a collection of all addresses
//     */
//    Page<Address> findAllAddresses(Pageable pageable);
//    
//    /**
//     * Updates the specified address
//     * 
//     * @param address address to update
//     */
//    Address updateAddress(Address address);
//    
//    /**
//     * Deletes the specified address and deletes the reference in every person (set it to null)
//     * 
//     * @param address person to address
//     */
//    void deleteAddress(Address address);
//    
//    /**
//     * Deletes the specified address (by id) and deletes the reference in every person (set it to null)
//     * 
//     * @param address person to address
//     */
//    void deleteAddress(Long id);
}