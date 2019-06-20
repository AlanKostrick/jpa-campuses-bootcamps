package org.wecancodeit.campusesbootcamps.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wecancodeit.campusesbootcamps.models.Campus;
import org.wecancodeit.campusesbootcamps.repos.CampusRepository;

@Controller
public class CampusController {

	@Resource
	private CampusRepository campusRepo;

	@RequestMapping("/all-campuses")
	public String findAll(Model model) {
		model.addAttribute("campusesAttribute", campusRepo.findAll());
		return "campusesTemplate";
	}

	@PostMapping("/add-campus")
	public String addCampus(String name) {
		Campus campusToAdd = new Campus(name);

		if (campusRepo.findByName(campusToAdd.getName()) == null) {
			campusRepo.save(campusToAdd);
		}

		return "redirect:/all-campuses";
	}

}
