package edu.mum.exam.controller;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.exam.service.AssessmentService;
import edu.mum.registration.controller.ControllerExceptionHandler;
import edu.mum.registration.domain.User;
import edu.mum.registration.service.UserService;

@Controller
@RequestMapping("assessments")
public class AssessmentController {
	
	private static final Logger logger = Logger.getLogger(ControllerExceptionHandler.class);
	
	@Autowired
	AssessmentService assessmentService;	
	@Autowired
	UserService userService;
	
	@RequestMapping(value= {"", "/"}, method=RequestMethod.GET)
	public String list(Model model, Principal pricipal) {
		User user = userService.getUserByUsername(pricipal.getName());	
		logger.debug(pricipal.getName());
		model.addAttribute("assessments", assessmentService.getUserAssessments(user.getId()));
		return "assessment/assessments";
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String showAssessmentDetail(@RequestParam("id") Long id, Model model) {
		model.addAttribute("assessment", assessmentService.getAssessmentById(id));		
		return "assessment/assessment";
	}

}
