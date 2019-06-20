package org.wecancodeit.campusesbootcamps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wecancodeit.campusesbootcamps.models.Bootcamp;
import org.wecancodeit.campusesbootcamps.models.Campus;
import org.wecancodeit.campusesbootcamps.repos.BootcampRepository;
import org.wecancodeit.campusesbootcamps.repos.CampusRepository;

@Component
public class Initializer implements CommandLineRunner {

	@Autowired
	private CampusRepository campusRepo;

	@Autowired
	private BootcampRepository bootcampRepo;

	@Override
	public void run(String... args) throws Exception {

		Campus cBus = new Campus("Columbus");
		campusRepo.save(cBus);
		Campus cleveland = new Campus("Cleveland");
		campusRepo.save(cleveland);

		bootcampRepo.save(new Bootcamp("Java", cBus));
		bootcampRepo.save(new Bootcamp("JavaScript", cBus));
		bootcampRepo.save(new Bootcamp("cSharp", cleveland));

	}

}
