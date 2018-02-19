package edu.mum.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.exam.domain.Question;
import edu.mum.exam.exception.QuestionNotFoundException;
import edu.mum.exam.service.QuestionService;

public class QuestionIdValidator implements ConstraintValidator<QuestionId, String>{
		
	@Autowired
	QuestionService questionService;
	
	@Override
	public void initialize(QuestionId arg0) {			
	}

	@Override
	public boolean isValid(String questionId, ConstraintValidatorContext context) {
		Question question;
		try {			
			question = questionService.getQuestionByquestionId(questionId);
			
		} catch (QuestionNotFoundException e) {
			return true;
		}		
		
		if(question!= null) {
			return false;
		}
		
		return true;
		
		
	}
	

}
