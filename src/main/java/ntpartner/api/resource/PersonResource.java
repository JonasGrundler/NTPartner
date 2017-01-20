package ntpartner.api.resource;

import org.springframework.hateoas.ResourceSupport;

import ntpartner.model.Address;
import ntpartner.model.Person;

public class PersonResource extends ResourceSupport {
	public static String NAME_DELIMITER = " ";
	
	public long id;
    public String name;
    public AddressResource address;
    
//    
        
//    private AddressResource fromAddress(Address addressModel) {
//    	AddressResource addressResource = new AddressResource();
//    	if (addressModel != null) {
//    		addressResource.fromAddress(addressModel);
//    	}
//    	return addressResource;
//    }
//    
//    private Address toAddress() {
//    	Address addressModel = new Address();
//    	if (this.address != null) {
//    		addressModel = this.address.toAddress();
//    	}
//    	return addressModel;
//    }
//
//    /**
//     * Convert this instance of API-Model (Resource) to the internal data model.
//     *
//     * @return the converted instance
//     */
//    public Person toPerson() {
//        Person toConvert = new Person(id);
//        toConvert.name.firstname = extractFirstname(this.name);
//        toConvert.name.lastname = extractLastname(this.name);
//        toConvert.address = toAddress();
//
//        return toConvert;
//    }
//    
//    private String toName(String firstname, String lastname) {
//    	return firstname + NAME_DELIMITER + lastname;
//    }
//    
//    private String extractFirstname(String name) {
//    	int index = name.indexOf(NAME_DELIMITER);
//    	return name.substring(0, index);
//    }
//    
//    private String extractLastname(String name) {
//    	int index = name.indexOf(NAME_DELIMITER);
//    	return name.substring(index + 1);
//    }
}
