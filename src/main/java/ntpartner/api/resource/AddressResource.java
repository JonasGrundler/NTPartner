package ntpartner.api.resource;

import org.springframework.hateoas.ResourceSupport;

import ntpartner.model.Address;

public class AddressResource extends ResourceSupport {
	public Long id;
	public String street;
    public String streetNumber;
    public String zipCode;
    public String city;
    
//    /**
//     * Create a API-Model (Resource) instance from the internal data model.
//     *
//     * @param addressModel instance of the internal-data model
//     * @return a converted AddressResource
//     */
//    public AddressResource fromAddress(Address addressModel) {
//    	this.id = addressModel.getId();
//    	this.street = addressModel.street;
//        this.streetNumber = addressModel.streetNo;
//        this.zipCode = addressModel.zipCode;
//        return this;
//    }
//
//    /**
//     * Convert this instance of API-Model (Resource) to the internal data model.
//     *
//     * @return the converted instance
//     */
//    public Address toAddress() {
//        Address toConvert = new Address(id);
//        toConvert.street = street;
//        toConvert.streetNo = streetNumber;
//        toConvert.street = street;
//        toConvert.streetNo = streetNumber;
//        toConvert.city = city;
//
//        return toConvert;
//    }
}
