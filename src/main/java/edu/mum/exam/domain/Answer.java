package edu.mum.exam.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Answer implements Serializable {
	private static final long serialVersionUID = 4277685387048491691L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/**
	 * The question that is this answer for.
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn
	private Question question;
	
	/**
	 * The question number of the corresponding question in the exam
	 */
	@NotNull
	private Integer questionNumber;

	/**
	 * Answer for the FreeText question
	 */
	private String description;
	
	/**
	 * Answer for the single choice and multiple choices question
	 */
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="answer_id")
	Set<AnswerChoice> choices;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Integer getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<AnswerChoice> getChoices() {
		List<AnswerChoice> result = new ArrayList<>(choices);
		result.sort((a,b) -> a.getQuestionChoice().getDisplayOrder() == null? 0 : 
			a.getQuestionChoice().getDisplayOrder().compareTo(b.getQuestionChoice().getDisplayOrder()));
		return result;
	}
	
	/**
	 * Create an Answer choice corresponding to a Question choice.
	 * @param theQuestion
	 */
	public void assignQuestion(Question theQuestion) {
		question = theQuestion;
		if (question.getType() == QuestionType.MultipleChoices || 
			question.getType() == QuestionType.SingleChoice) {
			choices = new HashSet<>();
			for (QuestionChoice questionChoice : question.getChoices()) {
				AnswerChoice answerChoice = new AnswerChoice();
				answerChoice.setQuestionChoice(questionChoice);
				choices.add(answerChoice);
			}			
		}
	}
}
