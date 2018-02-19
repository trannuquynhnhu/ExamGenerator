package edu.mum.exam.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.mum.exam.domain.Exam;
import edu.mum.exam.domain.ExamQuestion;
import edu.mum.exam.domain.Question;
import edu.mum.exam.domain.QuestionType;
import edu.mum.exam.domain.Subject;
import edu.mum.exam.service.ExamService;
import edu.mum.exam.service.QuestionService;
import edu.mum.exam.service.SubjectService;
import edu.mum.formatter.QuestionTypeFormatter;


@Controller
@RequestMapping("/exam")
@SessionAttributes("exam")
public class ExamController {
	@Autowired
	MessageSource messageSource;
	@Autowired
	private QuestionTypeFormatter questionTypeFormatter;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private SubjectService subjectService;	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.addCustomFormatter(questionTypeFormatter);
	}
	@Autowired
	private ExamService examService;
	
	@RequestMapping(value= {"","/"}, method=RequestMethod.GET)
	public String list(Model model) {
		Iterable<Exam> exams = examService.getAllExams();
		model.addAttribute("exams", exams);
		return "exam/exams";
	}
	
	@RequestMapping(value="/addExam",method=RequestMethod.GET)
	public String addExam(Model model)
	{
		model.addAttribute("exam",new Exam());
		return "exam/addExam";
	}
	@RequestMapping(value="/addExam",method=RequestMethod.POST)

	public String createExam(@Valid @ModelAttribute("exam") Exam exam, BindingResult result, Model model)

	{
       
		if(result.hasErrors())
		{
			return "exam/addExam";
		}
		

		exam.setSubject(subjectService.getSubjectById(exam.getSubject().getId()));
		model.addAttribute("exam",exam);
		model.addAttribute("examQuestion",new ExamQuestion());
		Iterable<Question> questions= questionService.getAllQuestions();		
		model.addAttribute("questions",questions);
		return "exam/exam";
	}
	@RequestMapping(value="/addExamQuestion",method=RequestMethod.POST)
	public String addQuestion(@Valid @ModelAttribute("examQuestion") ExamQuestion examQuestion,BindingResult result, ModelMap map,Model model)
	{
		if(result.hasErrors())
		{
			return "exam/exam";
		}
		Exam exam=(Exam) map.get("exam");
		examService.addExamQuestionToExam(exam,examQuestion);
		model.addAttribute("examQuestion",new ExamQuestion());
		return "redirect:examStatus";
	}
	@RequestMapping(value="/addExistingExamQuestion",method=RequestMethod.POST)
	public String addExistingExamQuestion(@Valid ExamQuestion examQuestion,BindingResult result, ModelMap map,Model model)
	{
		if(result.hasErrors())
		{
			return "exam/exam";
		}
		Exam exam=(Exam) map.get("exam");
		examService.addExamQuestionToExam(exam,examQuestion);
		
		model.addAttribute("examQuestion",new ExamQuestion());
		return "redirect:examStatus";
	}
	
	@RequestMapping(value="/examStatus",method=RequestMethod.GET)
	public String addQuestion(@ModelAttribute("examQuestion") ExamQuestion examQuestion,ModelMap map,Model model)
	{
				return "exam/exam";
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveExam(ModelMap map)
	{
		Exam exam=(Exam) map.get("exam");
		examService.save(exam);		
		return "exam/details";
	}
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String examDetail(@RequestParam("examId") String examId,Model model)
	{
		Exam exam=examService.getExamById(examId);
		model.addAttribute("exam",exam);		
		return "exam/details";
	}
	
	@ModelAttribute("subjects")
	Iterable<Subject> getSubjects(Locale locale){
		return subjectService.getAllSubjects();
	}
@RequestMapping(value="/addExistingQuestion",method=RequestMethod.GET)
	@ResponseBody
	public List<Question> listExistingQuestions(ModelMap map)
	{		
		Exam exam=(Exam) map.get("exam");		
		List<Question> filteredQuestions=examService.getFilteredQuestions(exam);	
		System.out.println(filteredQuestions.size());
		return filteredQuestions;
		
	}
}
