package monto.service.discovery;

import java.util.Arrays;
import java.util.List;

public class DiscoveryRequest {
	List<Filter> filters;

	public DiscoveryRequest(List<Filter> filters) {
		this.filters = filters;
	}
	
	public DiscoveryRequest(Filter ... filters) {
		this(Arrays.asList(filters));
	}

	public List<Filter> getFilters() {
		return filters;
	}
	
}
