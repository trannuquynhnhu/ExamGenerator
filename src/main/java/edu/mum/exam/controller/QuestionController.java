package edu.mum.exam.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.exam.domain.Level;
import edu.mum.exam.domain.Question;
import edu.mum.exam.domain.QuestionChoice;
import edu.mum.exam.domain.QuestionType;
import edu.mum.exam.domain.Subject;
import edu.mum.exam.exception.ImageNotSaveException;
import edu.mum.exam.service.QuestionService;
import edu.mum.exam.service.SubjectService;
import edu.mum.formatter.LevelFormatter;
import edu.mum.formatter.QuestionTypeFormatter;

@RequestMapping("/questions")
@Controller
public class QuestionController {
	
	@Autowired
	MessageSource messageSource;	
	@Autowired
	private QuestionTypeFormatter questionTypeFormatter;	
	@Autowired
	private LevelFormatter levelFormatter;			
	@Autowired
	private QuestionService questionService;	
	@Autowired
	private SubjectService subjectService;	
	@Autowired
	ServletContext servletContext;
		
	//region New/Detail/List Question
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.addCustomFormatter(questionTypeFormatter);
		binder.addCustomFormatter(levelFormatter);		
	}	
	
	@RequestMapping(value= {"","/"}, method=RequestMethod.GET)
	public String list(Model model) {
		Iterable<Question> questions = questionService.getAllQuestions();
		model.addAttribute("questions", questions);
		return "question/questions";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addQuestion(@ModelAttribute("question") Question question) {

		return "question/addQuestion";
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String showQuestionDetail(@RequestParam("id") Long id, Model model) {
		model.addAttribute("question", questionService.getQuestionById(id));		
		return "question/question";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String saveQuestion(@Valid @ModelAttribute("question") Question question, BindingResult result) {
		if (result.hasErrors()) return "question/addQuestion";	
				
		//Save image
		MultipartFile image = question.getImage();
 		String rootDirectory = servletContext.getRealPath("/"); 		
 			
		//isEmpty means file exists BUT NO Content
		if (image!=null && !image.isEmpty()) {
	       try {
	    	   String imagePath = rootDirectory+"\\resources\\images\\"+ question.getQuestionId() + ".png";
	    	   question.setImagePath(imagePath);
	    	   image.transferTo(new File(imagePath));
	       } catch (Exception e) {
			throw new ImageNotSaveException(question.getQuestionId(), "");
	       }
		}
		
		//Save question
		questionService.saveQuestion(question);
				
		return "redirect:/questions/";
	}
	
	
	//region Exam
	@RequestMapping(value="/addToExam", method=RequestMethod.GET)
	public String addQuestionToExam(@ModelAttribute("question") Question question,@RequestParam("examid") String examId,@RequestParam("subjectid") String subjectId,Model model) {
		model.addAttribute("examid",examId);
		model.addAttribute("subjectid",subjectId);
		return "question/addQuestionToExam";
	}
	
	@RequestMapping(value="/addToExam", method=RequestMethod.POST)
	public String saveQuestionAndAddToExam(@Valid @ModelAttribute("question") Question question, BindingResult result,@RequestParam("examid") String examId,RedirectAttributes ra) {
		if (result.hasErrors()) return "question/addQuestionToExam";
		
		//Save question
		questionService.saveQuestion(question);
		
		//Save image
		MultipartFile image = question.getImage();
 		String rootDirectory = servletContext.getRealPath("/"); 		
 			
		//isEmpty means file exists BUT NO Content
		if (image!=null && !image.isEmpty()) {
	       try {
	    	   image.transferTo(new File(rootDirectory+"\\resources\\images\\"+ question.getQuestionId() + ".png"));
	       } catch (Exception e) {
			throw new ImageNotSaveException();
	       }
		}
		
			ra.addFlashAttribute("newquestion",question);
			return "redirect:/exam/examStatus";
		
	}
	
	@ModelAttribute("questionTypes")
	Map<String, String> getQuestionTypes(Locale locale) {
		Map<String, String> questionTypes = new HashMap<>();
		for (QuestionType type : QuestionType.values()) {
			questionTypes.put(type.name(), 
					messageSource.getMessage(type.getClass().getSimpleName()+ "." + type.name(), null, locale));
		}
		return questionTypes;
	}	
		
	@ModelAttribute("levels")
	Map<String, String> getLevels(Locale locale) {
		Map<String, String> levels = new HashMap<>();
		for (Level level : Level.values()) {
			levels.put(level.name(), 
					messageSource.getMessage(level.getClass().getSimpleName()+ "." + level.name(), null, locale));
		}
		return levels;
	}
	
	@ModelAttribute("subjects")
	Iterable<Subject> getSubjects(Locale locale){
		return subjectService.getAllSubjects();
	}
}
