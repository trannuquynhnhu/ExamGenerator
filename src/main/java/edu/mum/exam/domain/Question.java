package edu.mum.exam.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.mum.validator.QuestionId;

@Entity
public class Question implements Serializable {
	private static final long serialVersionUID = -3791223009473505104L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	@NotEmpty
	@QuestionId
	private String questionId;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
	@JoinColumn(name="subject")
	@Valid
	private Subject subject;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Level level;
	
	@NotEmpty
	@Size(min=0, max=30000, message="{Size.description.validation}")
	private String description;
	
	@JsonIgnore
	@Transient
	private MultipartFile image;
	
	private String imagePath;
		
	@Enumerated(EnumType.STRING)
	@NotNull
	private QuestionType type;
		
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="question_id")
	private List<QuestionChoice> choices;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public List<QuestionChoice> getChoices() {
		return choices;
	}

	public void setChoices(List<QuestionChoice> choices) {
		this.choices = choices;
	}

	@Override
	public boolean equals(Object o)
	{
		if(o.getClass().equals(this.getClass()))
				{
				if (((Question) o).getQuestionId().equals(this.getQuestionId()))
						return true;
				}
		return false;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}	
}
