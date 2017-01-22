package ntpartner.api.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import ntpartner.api.AddressController;
import ntpartner.api.PersonController;
import ntpartner.common.PageLinks;
import ntpartner.model.Address;

@Component
public class AddressListResourceAssembler {
	
	@PageLinks(PersonController.class)
	public ListResource<AddressResource> build(Page<Address> page) {
		List<AddressResource> addressList = page.getContent().stream().map((address)->{ 
			AddressResourceAssembler assembler = new AddressResourceAssembler(AddressController.class, AddressResource.class);
			AddressResource resource = assembler.toResource(address);
			return resource;
		}).collect(Collectors.toList());
		ListResource<AddressResource> listResource = new ListResource<>(addressList);
		return listResource;
	}

}
