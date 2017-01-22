package ntpartner.api.resource;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import ntpartner.api.PersonController;
import ntpartner.model.Person;

public class PersonResourceAssembler extends ResourceAssemblerSupport<Person, PersonResource> {

	public PersonResourceAssembler(Class<?> controllerClass, Class<PersonResource> resourceType) {
		super(controllerClass, resourceType);
	}

	@Override
	public PersonResource toResource(Person entity) {
		PersonResource personResource = createResourceWithId(entity.getId(), entity);
		personResource.id = entity.getId();
		personResource.name = entity.name.firstname + " " + entity.name.lastname;
		
		if (entity.address != null) {
			AddressResourceAssembler addressResourceAssembler = new AddressResourceAssembler(PersonController.class, AddressResource.class);
			AddressResource addressResource = addressResourceAssembler.toResource(entity.address);
			personResource.address = addressResource;
		}
		return personResource;
	}

}
