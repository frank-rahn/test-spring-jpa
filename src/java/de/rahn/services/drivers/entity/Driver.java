package de.rahn.services.drivers.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import de.rahn.validation.NotNullOrBlank;

/**
 * Die Klasse eines Fahrers.
 * @author Frank W. Rahn
 */
@Entity
@Table(schema = "rahn")
@Access(AccessType.FIELD)
@NamedQueries(@NamedQuery(name = Driver.FIND_ALL, query = "from Driver d"))
public class Driver {

	/** Konstante für die NamedQuery. */
	public static final String FIND_ALL = "Driver.findAll";

	/** Der Identifizierer des Fahrers. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DriverSEQ")
	@SequenceGenerator(name = "DriverSEQ", sequenceName = "DriverSEQ",
		schema = "rahn")
	@Basic(optional = false)
	@NotNull
	private Long id;

	/** Der Name des Fahrers. */
	@Basic(optional = false)
	@Column(nullable = false)
	@NotNullOrBlank
	private String name;

	/** Der Vorname des Fahrers. */
	@Basic
	private String firstname;

	/** Die Autos die der Fahrer fährt. */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "driver_id", nullable = false)
	private Set<Car> cars = new HashSet<>();

	// Ab hier von Eclipse generierte Methoden hashCode(), equals(), toString()

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (cars != null && Persistence.getPersistenceUtil().isLoaded(cars)) {
			result = prime * result + cars.hashCode();
		} else {
			result = prime * result;
		}
		result =
			prime * result + (firstname == null ? 0 : firstname.hashCode());
		result = prime * result + (id == null ? 0 : id.hashCode());
		result = prime * result + (name == null ? 0 : name.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Driver)) {
			return false;
		}
		Driver other = (Driver) obj;
		if (cars == null) {
			if (other.cars != null) {
				return false;
			}
		} else {
			if (Persistence.getPersistenceUtil().isLoaded(cars)
				&& Persistence.getPersistenceUtil().isLoaded(other.cars)) {
				if (!cars.equals(other.cars)) {
					return false;
				}
			}
		}
		if (firstname == null) {
			if (other.firstname != null) {
				return false;
			}
		} else if (!firstname.equals(other.firstname)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder().append("Driver [id=").append(id)
			.append(", name=").append(name).append(", firstname=")
			.append(firstname).append("]").toString();
	}

	// Ab hier von Eclipse generierte Setter und Getter

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the cars
	 */
	public Set<Car> getCars() {
		return cars;
	}

	/**
	 * @param cars the cars to set
	 */
	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

}