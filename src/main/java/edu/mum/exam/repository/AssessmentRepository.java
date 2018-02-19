package edu.mum.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mum.exam.domain.Assessment;

@Repository
public interface AssessmentRepository extends CrudRepository<Assessment, Long> {
	
	@Query("SELECT a FROM Assessment a WHERE a.user.id = :userId")
	List<Assessment> findAssessmentsByUserId(@Param("userId") Long userId);
}
