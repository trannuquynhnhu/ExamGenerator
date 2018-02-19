package edu.mum.registration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.mum.exam.domain.Assessment;
import edu.mum.registration.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.userCredentials.username = :userName")
	User findUserByUsername(@Param("userName") String userName);
}
