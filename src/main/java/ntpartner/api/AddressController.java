package ntpartner.api;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ntpartner.api.resource.AddressListResourceAssembler;
import ntpartner.api.resource.AddressResource;
import ntpartner.api.resource.ListResource;
import ntpartner.boundary.PartnerBoundaryService;
import ntpartner.model.Address;

/**
 * REST-Service to access Address resources
 */
@RestController
@Transactional
@RequestMapping(value = AddressController.ADDRESS_RESOURCE_PATH, produces = { MediaType.APPLICATION_JSON_VALUE })
public class AddressController {
	public static final String ADDRESS_RESOURCE_PATH = "/v1/address";
	
	@Autowired
	private AddressListResourceAssembler addressListAssembler;

	@Autowired
	private PartnerBoundaryService partnerService;

	// @RequestMapping(value = PartnerController.ADDRESS_RESOURCE_PATH, method =
	// RequestMethod.POST)
	// public ResponseEntity<AddressResource> createAddress(@RequestBody
	// AddressResource addressRessource) {
	// if (addressRessource != null) {
	// Address address = addressRessource.toAddress();
	// Address newAddress = partnerService.createAddress(address);
	// AddressResource newAddressResource = new
	// AddressResource().fromAddress(newAddress);
	// return new ResponseEntity<AddressResource>(newAddressResource,
	// HttpStatus.CREATED);
	// } else {
	// return new ResponseEntity<AddressResource>(HttpStatus.BAD_REQUEST);
	// }
	// return new ResponseEntity<AddressResource>(HttpStatus.BAD_REQUEST);
	// }

	/**
	 * Retrieves all addresses in a pageable fashion.
	 *
	 * @param pageable parameter for creating pages
	 * @return a collection of all addresses
	 */
	@RequestMapping
	 public ListResource<AddressResource> findAllAddresses(Pageable pageable) {
		Page<Address> page = partnerService.findAllAddresses(pageable);
		ListResource<AddressResource> addressListResource = addressListAssembler.build(page);
		return addressListResource;
	}

	/**
	 * Updates the specified address
	 * 
	 * @param addressRessource
	 * @return
	 */
	// @RequestMapping(value = PartnerController.ADDRESS_RESOURCE_PATH, method =
	// RequestMethod.PUT)
	// public ResponseEntity<AddressResource> updateAddress(AddressResource
	// addressRessource) {
	// if (addressRessource != null) {
	// Address address = addressRessource.toAddress();
	// Address updatedAddress = partnerService.updateAddress(address);
	// AddressResource updatedAddressResource = new
	// AddressResource().fromAddress(updatedAddress);
	// return new ResponseEntity<AddressResource>(updatedAddressResource,
	// HttpStatus.OK);
	// } else {
	// return new ResponseEntity<AddressResource>(HttpStatus.BAD_REQUEST);
	// }
	// return new ResponseEntity<AddressResource>(HttpStatus.BAD_REQUEST);
	// }

	/**
	 * Deletes the specified address and deletes the reference in every person
	 * (set it to null)
	 * 
	 * @param id
	 *            address
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<AddressResource> delete(@PathVariable Long id) {
		partnerService.deleteAddress(id);
		return new ResponseEntity<AddressResource>(HttpStatus.OK);
	}
}