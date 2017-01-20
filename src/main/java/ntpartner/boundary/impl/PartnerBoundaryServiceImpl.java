package ntpartner.boundary.impl;

import java.util.List;

import ntpartner.boundary.PartnerBoundaryService;
import ntpartner.model.Address;
import ntpartner.model.Person;
import ntpartner.repository.AddressRepository;
import ntpartner.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PartnerBoundaryServiceImpl implements PartnerBoundaryService {

	private static final Pageable DEFAULT_PAGEABLE = new PageRequest(0, 10);

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Person createPartner(Person person) {
		Person newPerson = null;
		if (person != null) {
			newPerson = personRepository.save(person);
		}
		return newPerson;
	}

	@Override
	public Page<Person> findAllPartner(Pageable pageable) {
		if (pageable == null) {
			pageable = DEFAULT_PAGEABLE;
		}
		return personRepository.findAll(pageable);
	}

	@Override
	public Address createAddress(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public Page<Address> findAllAddresses(Pageable pageable) {
		if (pageable == null) {
			pageable = DEFAULT_PAGEABLE;
		}
		return addressRepository.findAll(pageable);
	}

	@Override
	public void deleteAddress(Address address) {
		List<Person> allPersons = personRepository.findByAddress(address.getId());
		for (Person person : allPersons) {
			person.address = null;
		}
		personRepository.save(allPersons);
		addressRepository.delete(address);
	}

	@Override
	public Person updatePartner(Person person) {
		Address newAddress = null;

		if (person.address.getId() == null) {
			// new Address
			newAddress = addressRepository.save(person.address);
			person.address = newAddress;
		} else {
			Address existingAddress = addressRepository.findOne(person.address.getId());
			if (!existingAddress.equals(person.address)) {
				// Something has changed in address object
				newAddress = addressRepository.save(person.address);
				person.address = newAddress;
			}
		}
		return personRepository.save(person);
	}

	@Override
	public void deletePartner(Person person) {
		personRepository.delete(person);

	}

	@Override
	public void deletePartner(Long id) {
		personRepository.delete(id);

	}

	@Override
	public Address updateAddress(Address address) {
		return addressRepository.save(address);

	}

	@Override
	public void deleteAddress(Long id) {
		addressRepository.delete(id);

	}

}
