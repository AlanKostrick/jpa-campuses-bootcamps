package org.wecancodeit.campusesbootcamps.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bootcamp {

	private String name;

	@ManyToOne // there are many Bootcamp(s) to one Campus
	private Campus campus;

	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	// default no args constructor required by JPA
	protected Bootcamp() {

	}

	public Bootcamp(String name, Campus campus) {
		this.name = name;
		this.campus = campus;
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
		Bootcamp other = (Bootcamp) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
