package org.wecancodeit.campusesbootcamps;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.wecancodeit.campusesbootcamps.controllers.CampusController;
import org.wecancodeit.campusesbootcamps.models.Campus;
import org.wecancodeit.campusesbootcamps.repos.CampusRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(CampusController.class)
public class CampusControllerMvcTest {

	@Resource
	private MockMvc mvc;

	@MockBean
	private CampusRepository campusRepo;

	@Mock
	private Campus campusOne;

	@Mock
	private Campus campusTwo;

	@Test
	public void shouldReturnAStatusOfOk() throws Exception {
		Collection<Campus> campuses = Arrays.asList(campusOne, campusTwo);
		when(campusRepo.findAll()).thenReturn(campuses);
		mvc.perform(get("/all-campuses")).andExpect(view().name(is("campusesTemplate")));
	}

	@Test
	public void addEmployeeTest() throws Exception {

		Campus campusToAdd = new Campus("San Diego");

		mvc.perform(post("/add-campus").contentType(MediaType.APPLICATION_JSON).content(toJson(campusToAdd)))
				.andExpect(status().is3xxRedirection());
	}

	private String toJson(Campus newCampus) {
		return newCampus.getName();
	}
}
