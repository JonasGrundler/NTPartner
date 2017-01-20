package ntpartner.api;

import ntpartner.api.resource.AddressResource;
import ntpartner.boundary.PartnerBoundaryService;
import ntpartner.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-Service to access Partner resources
 */
@RestController
@RequestMapping(path = AddressController.ADDRESS_RESOURCE_PATH, produces = { MediaType.APPLICATION_JSON_VALUE })
public class AddressController {
	public static final String ADDRESS_RESOURCE_PATH = "/educama/v1/address";

	@Autowired
	private PartnerBoundaryService partnerService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AddressResource> createAddress(@RequestBody AddressResource addressRessource) {
		if (addressRessource != null) {
			Address address = addressRessource.toAddress();
			Address newAddress = partnerService.createAddress(address);
			AddressResource newAddressResource = new AddressResource().fromAddress(newAddress);
			return new ResponseEntity<AddressResource>(newAddressResource, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<AddressResource>(HttpStatus.BAD_REQUEST);
		}
	}

	//
	// /**
	// * Retrieves all addresses in a pageable fashion.
	// *
	// * @param pageable parameter for creating pages
	// * @return a collection of all addresses
	// */
	// Page<Address> findAllAddresses(Pageable pageable);
	//
	
	/**
	 * Updates the specified address
	 * @param addressRessource
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<AddressResource> updateAddress(AddressResource addressRessource) {
		if (addressRessource != null) {
			Address address = addressRessource.toAddress();
			Address updatedAddress = partnerService.updateAddress(address);
			AddressResource updatedAddressResource = new AddressResource().fromAddress(updatedAddress);
			return new ResponseEntity<AddressResource>(updatedAddressResource, HttpStatus.OK);
		} else {
			return new ResponseEntity<AddressResource>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	/**
	 * Deletes the specified address and deletes the reference in every person
	 * (set it to null)
	 * @param addressRessource
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<AddressResource> deleteAddress(@RequestBody AddressResource addressRessource) {
		if (addressRessource != null) {
			Address address = addressRessource.toAddress();
			partnerService.deleteAddress(address);
			return new ResponseEntity<AddressResource>(HttpStatus.OK);
		} else {
			return new ResponseEntity<AddressResource>(HttpStatus.BAD_REQUEST);
		}
		
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public ResponseEntity<AddressResource> delete(@PathVariable Long id) {
		partnerService.deleteAddress(id);
		return new ResponseEntity<AddressResource>(HttpStatus.OK);
	}
}