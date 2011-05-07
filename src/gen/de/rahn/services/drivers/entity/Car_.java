package de.rahn.services.drivers.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Car.class)
public abstract class Car_ {

	public static volatile SingularAttribute<Car, String> id;
	public static volatile SingularAttribute<Car, String> type;

}

