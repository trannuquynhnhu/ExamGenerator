package edu.mum.exam.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class QuestionChoice implements Serializable {
	private static final long serialVersionUID = -529664210324943437L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	//@NotNull
	private Integer displayOrder;
	
	@NotEmpty
	private String description;
	
	private boolean isCorrect;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer sortOrder) {
		this.displayOrder = sortOrder;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String text) {
		this.description = text;
	}

	public boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
}
