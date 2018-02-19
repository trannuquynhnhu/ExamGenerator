package edu.mum.registration.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.registration.domain.User;
import edu.mum.registration.service.UserService;

@Controller
@RequestMapping("/users")

public class UserController {		
	
	@Autowired
	private UserService userService;

	@RequestMapping(value= {"","/"}, method=RequestMethod.GET)
	public String list(Model model) {
		Iterable<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "user/users";
	}
	
	@RequestMapping(value="/addUser", method=RequestMethod.GET)
	public String addUser(@ModelAttribute("user") User user) {		
		return "user/addUser";
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String showUserDetail(@RequestParam("id") Long id, Model model) {
		model.addAttribute("user", userService.getUserById(id));		
		return "user/detailUser";
	}
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
		if (result.hasErrors()) return "user/addUser";
		
		//Save user
		userService.saveUser(user);
		
		return "redirect:/users/";
	}

}