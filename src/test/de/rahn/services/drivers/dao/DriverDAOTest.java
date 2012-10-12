package de.rahn.services.drivers.dao;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.io.Serializable;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.rahn.services.drivers.entity.Driver;

/**
 * Die Testklasse für {@link DriverDAO}.
 * @author Frank W. Rahn
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/context-test.xml",
	"../drivers.xml" })
@Transactional
public class DriverDAOTest {

	@Autowired
	private DriverDAO driverDAO;

	/**
	 * Test method for {@link DriverDAO#getPrimaryKey(Driver)}.
	 */
	@Test
	public void testGetPrimaryKey() {
		Driver driver = new Driver();
		driver.setId(new Long(4711));

		Long id = driverDAO.getPrimaryKey(driver);
		assertThat("Primärer Key unterschiedlich", driver.getId(), is(id));
	}

	/**
	 * Test method for {@link DriverDAO#findAll()}.
	 */
	@Test
	public void testFindAll() {
		List<Driver> drivers = driverDAO.findAll();
		assertThat("Anzahl der Treffer ungleich", drivers.size(), is(1));

		Driver driver = drivers.get(0);
		assertThat("id ungleich", driver.getId(), is(0L));
		assertThat("firstname ungleich", driver.getFirstname(), is("Martin"));
		assertThat("name ungleich", driver.getName(), is("Rahn"));
	}

	/**
	 * Test method for
	 * {@link de.rahn.db.jpa.dao.AbstractGenericJpaDAO#create(Object)}.
	 */
	@Test
	public void testCreate() {
		Driver driver = new Driver();
		driver.setName("Rahn");
		driver.setFirstname("Frank");

		Long id = driverDAO.create(driver);
		assertThat("keine id geliefert", id, notNullValue());
		assertThat("ungleiche id", driver.getId(), is(id));

		Driver driver2 = driverDAO.findByPrimaryKey(id);
		assertThat("doch nicht gespeichert", driver2, notNullValue());
		assertThat("ungleicher Fahrer", driver2, sameInstance(driver));
	}

	/**
	 * Test method for
	 * {@link de.rahn.db.jpa.dao.AbstractGenericJpaDAO#save(Object)}.
	 */
	@Test
	public void testSave() {
		Driver driver = driverDAO.findByPrimaryKey(0L);
		assertThat("kein Fahrer gefunden", driver, notNullValue());

		driver.setFirstname("Peter");
		driverDAO.save(driver);

		Driver driver2 = driverDAO.findByPrimaryKey(0L);
		assertThat("kein Fahrer gefunden", driver2, notNullValue());
		assertThat("ungleicher Fahrer", driver2, sameInstance(driver));
	}

	/**
	 * Test method for
	 * {@link de.rahn.db.jpa.dao.AbstractGenericJpaDAO#remove(Object)}.
	 */
	@Test
	public void testRemoveEntity() {
		List<Driver> drivers = driverDAO.findAll();
		assertThat("Anzahl der Treffer ungleich", drivers.size(), is(1));
		Driver driver = drivers.get(0);

		driverDAO.remove(driver);

		drivers = driverDAO.findAll();
		assertThat("Anzahl der Treffer ungleich", drivers.size(), is(0));
	}

	/**
	 * Test method for
	 * {@link de.rahn.db.jpa.dao.AbstractGenericJpaDAO#findByPrimaryKey(Serializable)}
	 * .
	 */
	@Test
	public void testFindByPrimaryKey() {
		Driver driver = driverDAO.findByPrimaryKey(0L);
		assertThat("id ungleich", driver.getId(), is(0L));
		assertThat("firstname ungleich", driver.getFirstname(), is("Martin"));
		assertThat("name ungleich", driver.getName(), is("Rahn"));
	}

	/**
	 * Test method for
	 * {@link de.rahn.db.dao.AbstractGenericDAO#remove(Serializable)}.
	 */
	@Test
	public void testRemovePrimaryKey() {
		driverDAO.remove(0L);

		List<Driver> drivers = driverDAO.findAll();
		assertThat("Anzahl der Treffer ungleich", drivers.size(), is(0));
	}

}