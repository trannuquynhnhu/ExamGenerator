package edu.mum.exam.controller.flow;

import java.io.Serializable;

import edu.mum.exam.domain.Answer;
import edu.mum.exam.domain.Assessment;
import edu.mum.exam.domain.Exam;
import edu.mum.exam.domain.Question;

public class AssessmentWrapper implements Serializable {

	private static final long serialVersionUID = -7133850711914981763L;
	private Assessment assessment;
	private int currentIndex;
	
	// default constructor for used in web flow scopes.
	public AssessmentWrapper() {}
	
	public AssessmentWrapper(Assessment assessment) {
		this.assessment = assessment;
		currentIndex = 0;
	}
	
	public Answer getAnswer() {
		return assessment.getAnswers().get(currentIndex);
	}
	
	public boolean nextAnswer() {		
		if (currentIndex < assessment.getAnswers().size()) {
			currentIndex++;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean previousAnswer() {		
		if (currentIndex >= 0) {
			currentIndex--;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getHasNextAnswer() {
		return currentIndex < assessment.getAnswers().size() - 1;
	}
	
	public boolean getHasPreviousAnswer() {
		return currentIndex > 0;
	}
	
	public Assessment getAssessment() {
		return assessment;
	}
	
	public Exam getExam() {
		return assessment.getExam();
	}
	
	public Question getQuestion() {
		return getAnswer().getQuestion();
	}
	
	public int getQuestionNumber() {
		return currentIndex + 1;
	}
}
