package ntpartner.api.resource;

import org.springframework.hateoas.ResourceSupport;

public class PersonResource extends ResourceSupport {
	public static String NAME_DELIMITER = " ";
	
	public Long id;
    public String name;
    public AddressResource address;
}
