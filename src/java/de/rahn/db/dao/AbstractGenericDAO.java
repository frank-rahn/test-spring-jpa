package de.rahn.db.dao;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.slf4j.Logger;

/**
 * Eine abstrakte Implementierung der Schnittstelle {@link GenericDAO}.
 * @author Frank W. Rahn
 * @param <Entity> Die Klasse der Entität
 * @param <PrimaryKey> Die Klasse des primären Key
 */
public abstract class AbstractGenericDAO<Entity, PrimaryKey extends Serializable>
	implements GenericDAO<Entity, PrimaryKey> {

	/** Der zentrale Logger für die DAO's. */
	protected final static Logger logger = getLogger(GenericDAO.class);

	/** Die Klasse der Entität. */
	protected final Class<Entity> entityClass;

	/** Die Klasse des primären Key. */
	protected final Class<PrimaryKey> primaryKeyClass;

	{
		Class<?> clazz = getClass();
		ParameterizedType parameterizedType = null;
		while (parameterizedType == null
			|| parameterizedType.getActualTypeArguments().length < 2) {
			Type type = clazz.getGenericSuperclass();

			if (type instanceof ParameterizedType) {
				parameterizedType = (ParameterizedType) type;
				type = parameterizedType.getRawType();
			}

			if (type instanceof Class) {
				clazz = (Class<?>) type;
			}
		}
		Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

		@SuppressWarnings("unchecked")
		Class<Entity> entityClass = (Class<Entity>) actualTypeArguments[0];
		this.entityClass = entityClass;

		@SuppressWarnings("unchecked")
		Class<PrimaryKey> primaryKey =
			(Class<PrimaryKey>) actualTypeArguments[1];
		this.primaryKeyClass = primaryKey;
	}

	/**
	 * {@inheritDoc}
	 * @see GenericDAO#remove(java.io.Serializable)
	 */
	@Override
	public void remove(PrimaryKey key) {
		remove(findByPrimaryKey(key));
	}

}