package de.rahn.db.jpa.dao;

import java.io.Serializable;

import de.rahn.db.dao.GenericDAO;

/**
 * Eine Erweiterung der Schnittstelle {@link GenericDAO} für JPA.
 * @author Frank W. Rahn
 * @param <Entity> Die Klasse der Entität
 * @param <PrimaryKey> Die Klasse des primären Key
 * @see de.rahn.db.dao.GenericDAO
 */
public interface GenericJpaDAO<Entity, PrimaryKey extends Serializable> extends GenericDAO<Entity, PrimaryKey> {

	// Noch keine spezielle Definitionen.

}