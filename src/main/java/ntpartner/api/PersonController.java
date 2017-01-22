package ntpartner.api;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
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
@RequestMapping(value = PersonController.PERSON_RESOURCE_PATH, produces = { MediaType.APPLICATION_JSON_VALUE })
public class PersonController {
	public static final String PERSON_RESOURCE_PATH = "/v1/partner";
	
	@Autowired
	private PersonListResourceAssembler personListAssembler;

	@Autowired
	private PartnerBoundaryService partnerService;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AddressRepository addressRepository;

	/**
	 * Creates a partner
	 *
	 * @param person
	 *            create a new partner
	 * @return the created partner
	 */
	@RequestMapping(value = "/test")
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

	// @RequestMapping(value = PartnerController.PERSON_RESOURCE_PATH, method =
	// RequestMethod.POST )
	// @ResponseStatus( HttpStatus.CREATED )
	// @ResponseBody
	// public PersonResource createPartner(@RequestBody PersonResource person) {
	// Person newPerson = partnerService.createPartner(person);
	// }

	/**
	 * Retrieves all persons in a pageable fashion.
	 * 
	 * @param pageable
	 *            parameter for creating pages
	 * @return a collection of all persons
	 */
	@RequestMapping
	public ListResource<PersonResource> findAllPartner(Pageable pageable) {
		Page<Person> page = partnerService.findAllPartner(pageable);
		ListResource<PersonResource> personListResource = personListAssembler.build(page);
		return personListResource;
	}

	//
	// /**
	// * Updates the specified partner
	// *
	// * @param person person to update
	// */
	// Person updatePartner(Person person);
	//
	/**
	 * Deletes the specified partner (by id)
	 * 
	 * @param id
	 *            person to delete
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void deletePartner(Long id) {
		partnerService.deletePartner(id);
	}
}