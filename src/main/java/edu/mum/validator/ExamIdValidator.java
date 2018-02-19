package edu.mum.validator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


import edu.mum.exam.domain.Exam;

import edu.mum.exam.exception.ExamNotFoundException;

import edu.mum.exam.service.ExamService;


public class ExamIdValidator implements ConstraintValidator<ExamId, String>{
		
	@Autowired
	ExamService examService;
	
	@Override
	public void initialize(ExamId arg0) {			
	}

	@Override
	public boolean isValid(String examId, ConstraintValidatorContext context) {
		Exam exam;
		try {			
			exam = examService.getExamById(examId);
			
		} catch (ExamNotFoundException e) {
			return true;
		}		
		
		if(exam!= null) {
			return false;
		}
		
		return true;
		
		
	}
	

}
