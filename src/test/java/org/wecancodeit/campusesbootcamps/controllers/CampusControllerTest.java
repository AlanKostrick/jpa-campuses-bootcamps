package org.wecancodeit.campusesbootcamps.controllers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.wecancodeit.campusesbootcamps.controllers.CampusController;
import org.wecancodeit.campusesbootcamps.models.Campus;
import org.wecancodeit.campusesbootcamps.repos.CampusRepository;

public class CampusControllerTest {

	@InjectMocks
	private CampusController underTest;

	@Mock
	private CampusRepository campusRepo;

	@Mock
	private Campus campusOne;
	Long campusOneId = 1L;

	@Mock
	private Campus campusTwo;
	Long campusTwoId = 2L;

	@Mock
	private Model model;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddEmployeeToModel() {
		Collection<Campus> campuses = Arrays.asList(campusOne, campusTwo);
		when(campusRepo.findAll()).thenReturn(campuses);
		underTest.findAll(model);
		verify(model).addAttribute("campusesAttribute", campuses);
	}

}
