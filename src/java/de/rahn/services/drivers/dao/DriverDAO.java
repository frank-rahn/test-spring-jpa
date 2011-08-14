package de.rahn.services.drivers.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import de.rahn.db.dao.GenericDAO;
import de.rahn.db.jpa.dao.AbstractGenericJpaDAO;
import de.rahn.services.drivers.entity.Driver;

/**
 * Ein DAO für den Fahrer.
 * @author Frank W. Rahn
 * @param <Entity> Die Klasse der Entität
 * @param <PrimaryKey> Die Klasse des primären Key
 */
@Repository
public class DriverDAO extends AbstractGenericJpaDAO<Driver, Long> {

	/**
	 * {@inheritDoc}
	 * @see GenericDAO#getPrimaryKey(java.lang.Object)
	 */
	@Override
	public Long getPrimaryKey(Driver persistentObject) {
		return persistentObject.getId();
	}

	/**
	 * Suche alle Fahrer.
	 * @return alle Fahrer
	 */
	public List<Driver> findAll() {
		return getEntityManager().createNamedQuery(Driver.FIND_ALL,
			Driver.class).getResultList();
	}

}