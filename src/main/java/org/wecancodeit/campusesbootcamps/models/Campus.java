package org.wecancodeit.campusesbootcamps.models;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Campus {

	private String name;

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(mappedBy = "campus") // one Campus to many Bootcamp(s)
	private Collection<Bootcamp> bootcamps;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	// default no args constructor required by JPA
	protected Campus() {

	}

	public Campus(String name) {
		this.name = name;
	}

	public Collection<Bootcamp> getBootcamps() {
		return bootcamps;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campus other = (Campus) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
