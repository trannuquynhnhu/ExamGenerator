package edu.mum.exam.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mum.exam.domain.Subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {
	

}
