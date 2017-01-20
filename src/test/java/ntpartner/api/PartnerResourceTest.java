package ntpartner.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.test.context.junit4.SpringRunner;

import ntpartner.api.resource.AddressResource;
import ntpartner.api.resource.AddressResourceAssembler;
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
		AddressResource resource = null;
		List<Address> addressList = addressRepository.findAll();
		AddressResourceAssembler assembler = new AddressResourceAssembler(AddressController.class, AddressResource.class);
		resource = assembler.toResource(addressList.get(0));
		assertTrue(resource.hasLinks());
	}

	@Test
	public void checkTestDataAvailable() {
		List<Person> allPersons = personRepository.findAll();
		assertEquals(2, allPersons.size());
		List<Address> allAddresses = addressRepository.findAll();
		assertEquals(1, allAddresses.size());
	}

	@Test
	public void deleteAddressForPerson() {
		List<Person> persons = personRepository.findByFirstnameAndLastname("Marty", "Maredo");
		Person person = persons.get(0);
		assertEquals("Marty", person.name.firstname);
		assertEquals("Maredo", person.name.lastname);
		assertEquals("70013", person.address.zipCode);
		assertEquals("Stuttgart", person.address.city);
		person.address = null;
		Person savedPerson = personRepository.save(person);
		assertEquals(null, savedPerson.address);
		List<Address> allAddresses = addressRepository.findAll();
		assertEquals(1, allAddresses.size());
	}

	@Test
	public void deleteAddressUnsafe() {
		try {
			List<Address> allAddresses = addressRepository.findAll();
			for (Address address : allAddresses) {
				addressRepository.delete(address);
				fail();
			}
		} catch (DataIntegrityViolationException e) {
			assertTrue(e != null);
		}
	}

	@Test
	public void deleteAddressSafe() {
		List<Person> allPersons = personRepository.findAll();
		for (Person person : allPersons) {
			person.address = null;
			personRepository.save(person);
		}
		List<Address> allAddresses = addressRepository.findAll();
		for (Address address : allAddresses) {
			addressRepository.delete(address);
		}
		assertEquals(3, personRepository.count());
		assertEquals(0, addressRepository.count());
	}
	
	@Test
	public void deletePerson() {
		List<Person> persons = personRepository.findByFirstnameAndLastname("Marty", "Maredo");
		Person person = persons.get(0);
		personRepository.delete(person);
		
		assertEquals(2, personRepository.count());
		assertEquals(1, addressRepository.count());
	}
	
	@Test 
	public void pageThroughPersons() {
		String[] firstNames = new String[]{ "Marty", "Marge", "Mat" };
		int loopCount = 0;
		Pageable pageable = new PageRequest(0 /* first */, 2 /* one at a time */);
		Page<Person> page = null;
		do {
			page = personRepository.findAll(pageable);
			loopCount++;
			assertEquals(3, page.getTotalElements());
			if (loopCount != 2) {
				assertEquals(2, page.getNumberOfElements());
			} else {
				assertEquals(1, page.getNumberOfElements());
			}
			Person person = page.getContent().get(0);
			assertEquals(firstNames[pageable.getPageNumber()*2], person.name.firstname);
			pageable = pageable.next();
		} while (pageable.getPageNumber()*pageable.getPageSize() < page.getTotalElements());
		assertEquals(2,  loopCount);
	}
	
	@Test 
	public void emptyPageable() {
		Page<Person> page = personRepository.findAll((Pageable)null);
		assertEquals(3, page.getTotalElements());
		assertEquals(3, page.getContent().size());
	}
	
	@Test
	public void updatePersonsAddressWrongWay() {
		List<Person> persons = personRepository.findByFirstnameAndLastname("Marty", "Maredo");
		Person person = persons.get(0);
		person.name.lastname = "Montana";
		person.address.city = "Vaihingen";
		Person savedPerson = personRepository.save(person);
		assertEquals("Marty", savedPerson.name.firstname);
		assertEquals("Montana", savedPerson.name.lastname);
		assertTrue(!savedPerson.address.city.equals("Vaihingen"));
	}
	
	@Test
	public void updatePersonsAddressCorrect() {
		List<Person> persons = personRepository.findByFirstnameAndLastname("Marty", "Maredo");
		Person person = persons.get(0);
		person.name.lastname = "Montana";
		person.address.city = "Vaihingen";
		Address savedAddress = addressRepository.save(person.address);
		
		Person savedPerson = personRepository.save(person);
		assertEquals("Marty", savedPerson.name.firstname);
		assertEquals("Montana", savedPerson.name.lastname);
		assertEquals("Vaihingen", savedPerson.address.city);
		assertEquals(savedAddress, savedPerson.address);
	}
	
	@Test
	public void updatePerson() {
		List<Person> persons = personRepository.findByFirstnameAndLastname("Marty", "Maredo");
		Person person = persons.get(0);
		person.name.lastname = "Montana";
		Person savedPerson = personRepository.save(person);
		assertEquals("Marty", savedPerson.name.firstname);
		assertEquals("Montana", savedPerson.name.lastname);
	}

}
