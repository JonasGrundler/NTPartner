package ntpartner.api.resource;

import java.util.Collection;

import org.springframework.hateoas.ResourceSupport;

public class ListResource<T> extends ResourceSupport {
	private Collection<T> list;

	public Collection<T> getList() {
		return list;
	}
	
	public ListResource(Collection<T> list) {
		this.list = list;
	}

}
