package ntpartner.api;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ntpartner.api.resource.ListResource;
import ntpartner.api.resource.PersonListResourceAssembler;
import ntpartner.api.resource.PersonResource;
import ntpartner.boundary.PartnerBoundaryService;
import ntpartner.model.Address;
import ntpartner.model.Person;
import ntpartner.repository.AddressRepository;
import ntpartner.repository.PersonRepository;

/**
 * REST-Service to access Partner resources
 */
@RestController
@Transactional
@RequestMapping(value=PartnerController.PERSON_RESOURCE_PATH)
public class PartnerController {
	public static final String PERSON_RESOURCE_PATH = "/partner";
	
	@Autowired
	private PersonListResourceAssembler assembler;

    @Autowired
    private PartnerBoundaryService partnerService;
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private AddressRepository addressRepository;
 
    /**
     * Creates a partner
     *
     * @param person create a new partner
     * @return the created partner
     */
    @RequestMapping(value ="/test")
    public void createPartner(/* Person person */) {
    	Address address = new Address("Königstraße", "38", "70013", "Stuttgart");
		Address savedAddress = addressRepository.save(address);

		Person person = new Person("Marty", "Maredo");
		person.address = savedAddress;
		personRepository.save(person);

		person = new Person("Marge", "Maredo");
		person.address = savedAddress;
		personRepository.save(person);
		
		person = new Person("Mat", "Maredo");
		person.address = savedAddress;
		personRepository.save(person);
    }

    /**
     * Retrieves all persons in a pageable fashion.
     * 
     * @param pageable parameter for creating pages
     * @return a collection of all persons
     */
    @RequestMapping
    public ListResource<PersonResource> findAllPartner(Pageable pageable) {
    	Page<Person> page = partnerService.findAllPartner(pageable);
    	ListResource<PersonResource>personListResource = assembler.build(page);
    	return personListResource;
    }
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