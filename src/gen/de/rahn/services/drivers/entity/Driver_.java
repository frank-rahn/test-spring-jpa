package de.rahn.services.drivers.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor", date = "2012-10-12T19:50:48.224+0200")
@SuppressWarnings("all")
@StaticMetamodel(Driver.class)
public abstract class Driver_ {

	public static volatile SingularAttribute<Driver, Long> id;
	public static volatile SetAttribute<Driver, Car> cars;
	public static volatile SingularAttribute<Driver, String> name;
	public static volatile SingularAttribute<Driver, String> firstname;

}

