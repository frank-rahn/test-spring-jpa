package de.rahn.db.jpa.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.rahn.db.dao.AbstractGenericDAO;
import de.rahn.db.dao.GenericDAO;

/**
 * Eine Implementierung der Schnittstelle {@link GenericDAO} für JPA.
 * @author Frank W. Rahn
 * @param <Entity> Die Klasse der Entität
 * @param <PrimaryKey> Die Klasse des primären Key
 * @see de.rahn.db.dao.AbstractGenericDAO
 */
public abstract class AbstractGenericJpaDAO<Entity, PrimaryKey extends Serializable> extends
	AbstractGenericDAO<Entity, PrimaryKey> implements GenericJpaDAO<Entity, PrimaryKey> {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * @return Liefert den {@link #entityManager}
	 */
	protected final EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * {@inheritDoc}
	 * @see de.rahn.db.dao.GenericDAO#create(java.lang.Object)
	 */
	@Override
	public PrimaryKey create(Entity newPersistentObject) {
		entityManager.persist(newPersistentObject);
		return getPrimaryKey(newPersistentObject);
	}

	/**
	 * {@inheritDoc}
	 * @see de.rahn.db.dao.GenericDAO#save(java.lang.Object)
	 */
	@Override
	public void save(Entity persistentObject) {
		entityManager.merge(persistentObject);
	}

	/**
	 * {@inheritDoc}
	 * @see de.rahn.db.dao.GenericDAO#remove(java.lang.Object)
	 */
	@Override
	public void remove(Entity persistentObject) {
		entityManager.remove(persistentObject);
	}

	/**
	 * {@inheritDoc}
	 * @see de.rahn.db.dao.GenericDAO#findByPrimaryKey(java.io.Serializable)
	 */
	@Override
	public Entity findByPrimaryKey(PrimaryKey key) {
		return entityManager.find(entityClass, key);
	}

}