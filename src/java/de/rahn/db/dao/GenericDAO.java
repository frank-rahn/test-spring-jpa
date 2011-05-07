package de.rahn.db.dao;

import java.io.Serializable;

/**
 * Ein generische DAO-Schnittestelle für eine Entität mit einem primären Key.
 * @author Frank W. Rahn
 * @param <Entity> Die Klasse der Entität
 * @param <PrimaryKey> Die Klasse des primären Key
 */
public interface GenericDAO<Entity, PrimaryKey extends Serializable> {

	/**
	 * Liefere den primären Key für das angegebene Objekt.
	 * @param persistentObject das persistente Objekt
	 * @return den primären Key
	 */
	PrimaryKey getPrimaryKey(Entity persistentObject);

	/**
	 * Speichere das neue Objekt in der Datenbank.
	 * @param newPersistentObject das neue persistente Objekt
	 * @return den primären Key
	 */
	PrimaryKey create(Entity newPersistentObject);

	/**
	 * Aktualisiere das geänderte Objekt in der Datenbank.
	 * @param persistentObject das persistente Objekt
	 */
	void save(Entity persistentObject);

	/**
	 * Lösche das persistente Objekt aus der Datenbank.
	 * @param persistentObject das persistente Objekt
	 */
	void remove(Entity persistentObject);

	/**
	 * Lösche das persistente Objekt aus der Datenbank.
	 * @param key der primäre Key
	 */
	void remove(PrimaryKey key);

	/**
	 * Finde das persistente Objekt an Hand seines primären Keys.
	 * @param key der primäre Key
	 * @return das persistente Objekt
	 */
	Entity findByPrimaryKey(PrimaryKey key);

}