package de.rahn.db.jpa.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Ein typisches Spring-Template, dass die Erzeugung einiger notwendigen Factory
 * Klassen zur Erzeugung von Queries auf Basis der Criteria API an das
 * {@link AbstractGenericJpaDAO} deligiert.
 * @author Frank W. Rahn
 * @param <Entity> Die Klasse der Entität
 * @see AbstractGenericJpaDAO
 */
public interface CriteriaQueryTemplate<Entity> {

	/**
	 * Diese Methode wird durch das {@link AbstractGenericJpaDAO} ausgeführt und
	 * stellt einige Standardkomponenten der Criteria API zu Verfügung.
	 * @param builder der {@link CriteriaBuilder}
	 * @param query die an die Entität gebundene Abfrage
	 * @param rootEntity die Projektionsvariable der FROM-Klausel
	 */
	void doBuild(CriteriaBuilder builder, CriteriaQuery<Entity> query,
		Root<Entity> rootEntity);

}