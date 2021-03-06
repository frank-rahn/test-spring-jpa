package de.rahn.db.jpa.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.rahn.db.dao.AbstractGenericDAO;
import de.rahn.db.dao.GenericDAO;

/**
 * Eine Implementierung der Schnittstelle {@link GenericJpaDAO} für JPA.
 * @author Frank W. Rahn
 * @param <Entity> Die Klasse der Entität
 * @param <PrimaryKey> Die Klasse des primären Key
 * @see de.rahn.db.dao.AbstractGenericDAO
 */
public abstract class AbstractGenericJpaDAO<Entity, PrimaryKey extends Serializable>
	extends AbstractGenericDAO<Entity, PrimaryKey> implements
	GenericJpaDAO<Entity, PrimaryKey> {

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
	 * @see GenericDAO#create(java.lang.Object)
	 */
	@Override
	public PrimaryKey create(Entity newPersistentObject) {
		entityManager.persist(newPersistentObject);
		return getPrimaryKey(newPersistentObject);
	}

	/**
	 * {@inheritDoc}
	 * @see GenericDAO#save(java.lang.Object)
	 */
	@Override
	public void save(Entity persistentObject) {
		entityManager.merge(persistentObject);
	}

	/**
	 * {@inheritDoc}
	 * @see GenericDAO#remove(java.lang.Object)
	 */
	@Override
	public void remove(Entity persistentObject) {
		entityManager.remove(persistentObject);
	}

	/**
	 * {@inheritDoc}
	 * @see GenericDAO#findByPrimaryKey(java.io.Serializable)
	 */
	@Override
	public Entity findByPrimaryKey(PrimaryKey key) {
		return entityManager.find(entityClass, key);
	}

	/**
	 * Erzeuge über die Criteria API eine JPA Query.
	 * @param template der Builder
	 * @return eine ausführbare JPA Query
	 */
	protected TypedQuery<Entity> buildQuery(
		CriteriaQueryTemplate<Entity> template) {

		// Erzeuge eine Builder
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		// Erzeuge die Query
		CriteriaQuery<Entity> criteriaQuery =
			criteriaBuilder.createQuery(entityClass);

		// Erzeuge die Referenz zur Hauptentität
		Root<Entity> rootEntity = criteriaQuery.from(entityClass);

		// Führe den Build durch
		template.doBuild(criteriaBuilder, criteriaQuery, rootEntity);

		// Erzeuge eine ausführbare JPA Query
		return entityManager.createQuery(criteriaQuery);
	}

}