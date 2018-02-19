package edu.mum.exam.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.exam.domain.Exam;
import edu.mum.exam.domain.ExamQuestion;
import edu.mum.exam.domain.Question;
import edu.mum.exam.repository.ExamRepository;
import edu.mum.exam.service.ExamService;
import edu.mum.exam.service.QuestionService;
@Service
@Transactional
public class ExamServiceImpl implements ExamService{
	@Autowired
	ExamRepository examRepository;
	@Autowired
	QuestionService questionService;
	public Iterable<Exam> getAllExams(){
		return examRepository.findAll();
	}
	public Exam save(Exam exam)
	{
		return examRepository.save(exam);
	}
	public Exam getExamById(String examId)
	{
		return examRepository.getExamByExamId(examId);
	}
	public ExamQuestion saveExamQuestion(ExamQuestion examQuestion)
	{
		return examRepository.save(examQuestion);
	}
	public void addExamQuestionToExam(Exam exam,ExamQuestion examQuestion)
	{
		examQuestion.setExam(exam);
		examQuestion.setQuestion(questionService.getQuestionByquestionId(examQuestion.getQuestion().getQuestionId()));		
		if(exam.getQuestions()==null) { exam.setQuestions(new ArrayList<ExamQuestion>());}	
		examQuestion.setQuestionNumber(exam.getQuestions().size()+1);
		exam.getQuestions().add(examQuestion);
		save(exam);
		saveExamQuestion(examQuestion);
	}
	public List<Question> getFilteredQuestions(Exam exam)
	{
		List<Question> examquestions=new ArrayList<>();
		if(exam.getQuestions()==null) { exam.setQuestions(new ArrayList<ExamQuestion>());}
		for(ExamQuestion eq:exam.getQuestions())
		{
			examquestions.add(eq.getQuestion());
		}
		Iterable<Question> questions= questionService.getAllQuestionsBySubjectId(exam.getSubject().getId());
		List<Question> filteredQuestions=new ArrayList<>();
		for(Question q:questions)
		{
			if(!examquestions.contains(q))
			{
				filteredQuestions.add(q);
			}
		}
		return filteredQuestions;
	}
	
}
