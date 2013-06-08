package de.rahn.services.drivers.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import de.rahn.db.dao.GenericDAO;
import de.rahn.db.jpa.dao.AbstractGenericJpaDAO;
import de.rahn.db.jpa.dao.CriteriaQueryTemplate;
import de.rahn.services.drivers.entity.Driver;
import de.rahn.services.drivers.entity.Driver_;

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

	/**
	 * Suche alle Fahrer mit dem Namen.
	 * @param name der Name des Fahrers
	 * @return die gefundenen Fahrer
	 */
	public List<Driver> findByName(final String name) {
		return buildQuery(new CriteriaQueryTemplate<Driver>() {

			@Override
			public void doBuild(CriteriaBuilder builder,
				CriteriaQuery<Driver> query, Root<Driver> rootEntity) {
				// Erzeuge eine logische Ausdruck
				Predicate predicate =
					builder.equal(rootEntity.get(Driver_.name), name);

				// Definiere die Abfrage
				query.select(rootEntity).where(predicate).distinct(true);
			}
		}).getResultList();
	}

}