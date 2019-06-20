package org.wecancodeit.campusesbootcamps.repos;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.campusesbootcamps.models.Campus;

public interface CampusRepository extends CrudRepository<Campus, Long> {

	Campus findByName(String name);
	
}
