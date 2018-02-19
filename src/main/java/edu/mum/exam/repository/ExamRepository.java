package edu.mum.exam.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mum.exam.domain.Exam;
import edu.mum.exam.domain.ExamQuestion;

@Repository
public interface ExamRepository extends CrudRepository<Exam, Long> {	
	
	public Exam getExamByExamId(String examId);

	public ExamQuestion save(ExamQuestion examQuestion);

}


