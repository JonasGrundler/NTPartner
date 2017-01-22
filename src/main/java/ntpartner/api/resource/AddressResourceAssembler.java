package ntpartner.api.resource;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import ntpartner.model.Address;

public class AddressResourceAssembler extends ResourceAssemblerSupport<Address, AddressResource> {

	public AddressResourceAssembler(Class<?> controllerClass, Class<AddressResource> resourceType) {
		super(controllerClass, resourceType);
	}

	@Override
	public AddressResource toResource(Address entity) {
		AddressResource addressResource = createResourceWithId(entity.getId(), entity);
		addressResource.id = entity.getId();
		addressResource.street = entity.street;
		addressResource.streetNo = entity.streetNo;
		addressResource.city = entity.city;
		addressResource.zipCode = entity.zipCode;
		return addressResource;
	}

}
