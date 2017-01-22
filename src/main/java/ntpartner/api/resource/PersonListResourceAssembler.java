package ntpartner.api.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import ntpartner.api.PersonController;
import ntpartner.common.PageLinks;
import ntpartner.model.Person;

@Component
public class PersonListResourceAssembler {
	@PageLinks(PersonController.class)
	public ListResource<PersonResource> build(Page<Person> page) {
		List<PersonResource> personList = page.getContent().stream().map((person)->{ 
			PersonResourceAssembler assembler = new PersonResourceAssembler(PersonController.class, PersonResource.class);
			PersonResource resource = assembler.toResource(person);		
			return resource;
		}).collect(Collectors.toList());
		ListResource<PersonResource> listResource = new ListResource<>(personList);
		return listResource;
	}

}
