package ntpartner.api.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import ntpartner.api.PartnerController;
import ntpartner.common.PageLinks;
import ntpartner.model.Person;

@Component
public class PersonListResourceAssembler {
	@PageLinks(PartnerController.class)
	public ListResource<PersonResource> build(Page<Person> page) {
		List<PersonResource> personList = page.getContent().stream().map((person)->{ 
			PersonResource resource = new PersonResource();
			resource.id = person.getId();
			resource.name = person.name.firstname + " " + person.name.lastname;
			resource.address = new AddressResource();
			resource.address.id = person.address.getId();
			resource.address.street = person.address.street;
			resource.address.streetNumber = person.address.streetNo;
			resource.address.zipCode = person.address.zipCode;
			resource.address.city = person.address.city;
			
			return resource;
		}).collect(Collectors.toList());
		ListResource<PersonResource> listResource = new ListResource<>(personList);
		return listResource;
	}

}
