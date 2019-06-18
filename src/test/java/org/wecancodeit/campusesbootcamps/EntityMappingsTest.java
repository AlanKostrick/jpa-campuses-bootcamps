package org.wecancodeit.campusesbootcamps;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
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

		Campus foundCampus = campusRepo.findById(cBus.getId()).get();
		assertThat(foundCampus.getName(), is("Columbus"));
		// or use the AssertJ assertThat
		assertThat(foundCampus.getName()).isEqualTo(cBus.getName());
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

}
