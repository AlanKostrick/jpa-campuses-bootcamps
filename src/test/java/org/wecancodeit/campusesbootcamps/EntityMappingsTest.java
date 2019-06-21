package org.wecancodeit.campusesbootcamps;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.wecancodeit.campusesbootcamps.models.Bootcamp;
import org.wecancodeit.campusesbootcamps.models.Campus;
import org.wecancodeit.campusesbootcamps.repos.BootcampRepository;
import org.wecancodeit.campusesbootcamps.repos.CampusRepository;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EntityMappingsTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CampusRepository campusRepo;

	@Autowired
	private BootcampRepository bootcampRepo;

	@Test
	public void shouldSaveAndLoadACampus() {
		Campus cBus = new Campus("Columbus");
		entityManager.persist(cBus);
		entityManager.flush();

		Optional<Campus> campusOptional = campusRepo.findById(cBus.getId());
		Campus foundCampus = campusOptional.get();
		assertThat(foundCampus.getName(), is("Columbus"));
		// or use the AssertJ assertThat
		assertThat(foundCampus.getName()).isEqualTo(cBus.getName());
		assertTrue(campusOptional.isPresent());
	}
	@Test
	public void shouldReturnEmptyOptionalIfNoValueExists() {
		Optional<Campus> campusOptional = campusRepo.findById(110L);
		Campus foundCampus = campusOptional.get();
		assertNotNull(foundCampus);
//		assertFalse(campusOptional.isPresent());
	}

	@Test
	public void shouldSaveAndLoadABootcamp() {
		Campus cBus = new Campus("Columbus");
		entityManager.persist(cBus);

		Bootcamp java = new Bootcamp("Java", cBus);
		entityManager.persist(java);

		entityManager.flush();
		Bootcamp foundBootcamp = bootcampRepo.findById(java.getId()).get();
		assertThat(foundBootcamp.getName(), is("Java"));
	}

	@Test
	public void shouldShowAllBootcampsAtACampus() {
		Campus cBus = new Campus("Columbus");
		campusRepo.save(cBus);
		Campus cleveland = new Campus("Cleveland");
		campusRepo.save(cleveland);

		Bootcamp java = new Bootcamp("Java", cBus);
		bootcampRepo.save(java);
		Bootcamp javaScript = new Bootcamp("JavaScript", cBus);
		bootcampRepo.save(javaScript);
		Bootcamp cSharp = new Bootcamp("cSharp", cleveland);
		bootcampRepo.save(cSharp);

		entityManager.flush();
		entityManager.clear();

		Campus foundCampus = campusRepo.findById(cBus.getId()).get();

		Collection<Bootcamp> bootcamps = foundCampus.getBootcamps();
		System.out.println("The bootcamps seen are " + bootcamps);
		assertThat(bootcamps, containsInAnyOrder(java, javaScript));
	}
	
	@Test
	public void shouldFindCampusByName() {
		Campus campusToFind = new Campus("San Diego");
		campusRepo.save(campusToFind);
		Campus foundCampus = campusRepo.findByName("San Diego");
		assertThat(campusToFind, is(foundCampus));
	}

}
