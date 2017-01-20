package ntpartner.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.hateoas.Identifiable;

/**
 * Person Entity
 */
@Entity
public class Person extends AbstractPersistable<Long> implements Identifiable<Long> {

	private static final long serialVersionUID = -7728962437347271840L;

	
	/**
     * Constructor to be able to set id of entity explicitly
     * @param id Primary key of address
     */
	public Person(Long id) {
		setId(id);
	}
	
	public Person() {
	}

	public Person(String firstname, String lastname) {
		this.name = new Name(firstname, lastname);
	}
	
	@Embedded
	public Name name;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(foreignKey=@ForeignKey(name="Person_ibfk_1"))
	public Address address;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
