package de.rahn.services.drivers.standard;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.rahn.services.drivers.Drivers;
import de.rahn.services.drivers.entity.Car;
import de.rahn.services.drivers.entity.Driver;

/**
 * Die Testklasse für {@link StandardDrivers}.
 * @author Frank W. Rahn
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/context-test.xml",
	"../drivers.xml" })
@Transactional
public class StandardDriversTest {

	/** CAR_TYPE */
	private static final String CAR_TYPE = "Audi A6";

	/** CAR_ID */
	private static final String CAR_ID = "K-XX 4711";

	@Autowired
	private Drivers drivers;

	/**
	 * Test method for {@link StandardDrivers#getDriver(Long)}.
	 */
	@Test
	public void testGetDriver() {
		Driver driver = drivers.getDriver(0L);
		assertThat("kein Fahrer geliefert", driver, notNullValue());
		assertThat("id ungleich", driver.getId(), is(0L));
		assertThat("firstname ungleich", driver.getFirstname(), is("Martin"));
		assertThat("name ungleich", driver.getName(), is("Rahn"));
	}

	/**
	 * Test method for {@link StandardDrivers#createDriver(String, String)}.
	 */
	@Test
	public void testCreateDriver() {
		Long id = drivers.createDriver("Rahn", "Frank");
		assertThat("keine id geliefert", id, notNullValue());

		Driver driver = drivers.getDriver(id);
		assertThat("doch nicht gespeichert", driver, notNullValue());
		assertThat("ungleiche id", driver.getId(), is(id));
		assertThat("firstname ungleich", driver.getFirstname(), is("Frank"));
		assertThat("name ungleich", driver.getName(), is("Rahn"));
	}

	/**
	 * Test method for {@link StandardDrivers#addCarToDriver(Long, Car)}.
	 */
	@Test
	public void testAddCarToDriver() {
		Car car = new Car();
		car.setId(CAR_ID);
		car.setType(CAR_TYPE);
		Driver driver = drivers.addCarToDriver(0L, car);
		assertThat("doch nicht gespeichert", driver, notNullValue());
		assertThat("id ungleich", driver.getId(), is(0L));
		assertThat("firstname ungleich", driver.getFirstname(), is("Martin"));
		assertThat("name ungleich", driver.getName(), is("Rahn"));
		assertThat("anzahl Autos ungleich", driver.getCars().isEmpty(),
			not(true));
		car = driver.getCars().iterator().next();
		assertThat("id ungleich", car.getId(), is(CAR_ID));
		assertThat("type ungleich", car.getType(), is(CAR_TYPE));
	}

}