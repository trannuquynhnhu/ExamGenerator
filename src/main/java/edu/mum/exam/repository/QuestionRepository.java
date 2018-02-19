package edu.mum.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mum.exam.domain.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
	@Query("select q from Question q order by q.id")
	List<Question> getAllSortedQuestions();
	Question getQuestionByquestionId(String questionId);
	@Query("select q from Question q  where q.subject.id = :subjectid order by q.id")
	List<Question> getAllQuestionsBySubjectId(@Param("subjectid") long subjectid);
}
