package edu.mum.exam.service;

import java.util.List;

import edu.mum.exam.domain.Question;

public interface QuestionService {
	Iterable<Question> getAllQuestions();
	Question saveQuestion(Question question);
	Question getQuestionByquestionId(String questionId);
	Question getQuestionById(long id);
	List<Question> getAllQuestionsBySubjectId(long subjectid);
	
}
