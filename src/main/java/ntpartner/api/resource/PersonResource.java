package ntpartner.api.resource;

import ntpartner.model.Address;
import ntpartner.model.Person;

public class PersonResource {
	public static String NAME_DELIMITER = ",";
	
	public long id;
    public String name;
    public AddressResource address;
    
    /**
     * Create a API-Model (Resource) instance from the internal data model.
     *
     * @param personModel instance of the internal-data model
     * @return a converted PersonResource
     */
    public PersonResource fromPerson(Person personModel) {
    	this.id = personModel.getId();
    	this.name = toName(personModel.firstname, personModel.lastname);
        this.address = fromAddress(personModel.address);
        return this;
    }
    
    private AddressResource fromAddress(Address addressModel) {
    	AddressResource addressResource = new AddressResource();
    	if (addressModel != null) {
    		addressResource.fromAddress(addressModel);
    	}
    	return addressResource;
    }
    
    private Address toAddress() {
    	Address addressModel = new Address();
    	if (this.address != null) {
    		addressModel = this.address.toAddress();
    	}
    	return addressModel;
    }

    /**
     * Convert this instance of API-Model (Resource) to the internal data model.
     *
     * @return the converted instance
     */
    public Person toPerson() {
        Person toConvert = new Person(id);
        toConvert.firstname = extractFirstname(this.name);
        toConvert.lastname = extractLastname(this.name);
        toConvert.address = toAddress();

        return toConvert;
    }
    
    private String toName(String firstname, String lastname) {
    	return lastname + NAME_DELIMITER + firstname;
    }
    
    private String extractFirstname(String name) {
    	int index = name.indexOf(NAME_DELIMITER);
    	return name.substring(index + 1);
    }
    
    private String extractLastname(String name) {
    	int index = name.indexOf(NAME_DELIMITER);
    	return name.substring(0, index);
    }
}
