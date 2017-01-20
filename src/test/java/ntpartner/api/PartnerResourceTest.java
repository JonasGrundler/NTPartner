package ntpartner.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.test.context.junit4.SpringRunner;

import ntpartner.api.resource.ListResource;
import ntpartner.api.resource.PersonListResourceAssembler;
import ntpartner.api.resource.PersonResource;
import ntpartner.boundary.PartnerBoundaryService;
import ntpartner.model.Address;
import ntpartner.model.Person;
import ntpartner.repository.AddressRepository;
import ntpartner.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class PartnerResourceTest {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private PartnerBoundaryService partnerService;

	@Autowired
	private PersonListResourceAssembler assembler;

	@Before
	public void createTestData() {
		this.deleteTestData();
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

	@After
	public void deleteTestData() {
		List<Person> allPersons = personRepository.findAll();
		for (Person person : allPersons) {
			person.address = null;
			personRepository.delete(person);
		}

		List<Address> allAddresses = addressRepository.findAll();
		for (Address address : allAddresses) {
			addressRepository.delete(address);
		}
	}

	@Test
	public void createAddressResource() {
		PageRequest pageRequest = new PageRequest(0, 1);
		Page<Person> page = partnerService.findAllPartner(pageRequest);
		ListResource<PersonResource> personListResource = assembler.build(page);
		assertTrue(personListResource != null);
		assertEquals(1, personListResource.getList().size());
	}

}
