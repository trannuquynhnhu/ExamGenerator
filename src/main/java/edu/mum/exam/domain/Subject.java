package edu.mum.exam.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Subject implements Serializable {
	private static final long serialVersionUID = 4355570142455944122L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@NotNull(message="{NotNull.question.subject}")
	private Long id;
	
	
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

}
