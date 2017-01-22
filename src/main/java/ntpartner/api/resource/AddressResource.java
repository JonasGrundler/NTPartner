package ntpartner.api.resource;

import org.springframework.hateoas.ResourceSupport;

public class AddressResource extends ResourceSupport {
	public Long id;
	public String street;
    public String streetNo;
    public String zipCode;
    public String city;
}
