package edu.mum.exam.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.mum.exam.domain.Exam;
import edu.mum.exam.domain.Question;
import edu.mum.exam.service.ExamService;
import edu.mum.exam.service.QuestionService;


@SessionAttributes("exam")
@RestController
@RequestMapping("/exam")
public class ExamRestController {
	@Autowired
	MessageSource messageSource;
	
	
	@Autowired
	private ExamService examService;
	
	
	@RequestMapping(value="/addExistingQuestionOld",method=RequestMethod.GET)
	
	public List<Question> listExistingQuestions(ModelMap map)
	{		
		Exam exam=(Exam) map.get("exam");		
		List<Question> filteredQuestions=examService.getFilteredQuestions(exam);	
		System.out.println(filteredQuestions.size());
		return filteredQuestions;
		
	}
	
}