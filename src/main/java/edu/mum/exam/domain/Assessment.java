package edu.mum.exam.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
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

import edu.mum.registration.domain.User;

@Entity
public class Assessment implements Serializable {	
	private static final long serialVersionUID = -8424889810656014379L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn
	private Exam exam;
	
	/**
	 * All the answers of exam questions given by the student.
	 */
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="assessment_id")
	private Set<Answer> answers;
	
	/**
	 * The total core calculated base on the answers of student.
	 */
	private Integer score;
	
	/**
	 * The time at that student begins the exam.
	 */
	private Date startTime;
	
	/**
	 * The time at that student finish the exam.
	 */
	private Date endTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	/**
	 * Sort the Answers by QuestionNumber before returning the list.
	 * @return
	 */
	public List<Answer> getAnswers() {
		List<Answer> result = new ArrayList<>(answers);
		result.sort((Answer a, Answer b) -> a.getQuestionNumber() == null ? 0 : a.getQuestionNumber().compareTo(b.getQuestionNumber()));
		return result;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * Create all Answers for all Questions in the given exam.
	 * @param theExam
	 */
	public void assignExam(Exam theExam) {
		exam = theExam;
		answers = new HashSet<>();
		for (ExamQuestion examQuestion : exam.getQuestions()) {
			Answer answer = new Answer();
			answer.setQuestionNumber(examQuestion.getQuestionNumber());
			answer.assignQuestion(examQuestion.getQuestion());
			answers.add(answer);
		}
	}
}
