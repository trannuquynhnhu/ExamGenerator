package edu.mum.exam.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import edu.mum.exam.domain.Assessment;
import edu.mum.exam.domain.Exam;
import edu.mum.exam.exception.ExamNotFoundException;
import edu.mum.exam.repository.AssessmentRepository;
import edu.mum.exam.repository.ExamRepository;
import edu.mum.exam.service.AssessmentService;
import edu.mum.registration.domain.User;
import edu.mum.registration.repository.UserRepository;

@Service
@Transactional
public class AssessmentServiceImpl implements AssessmentService {
	
	@Autowired
	AssessmentRepository assessmentRepository;	
	@Autowired
	ExamRepository examRepository;
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Assessment CreateAssessmentForExam(Long examId) {
		Assessment assessment = new Assessment();
		Exam exam = examRepository.findOne(examId);
		if (exam == null) {
			throw new ExamNotFoundException(String.valueOf(examId), "Cannot find the exam by Id:");
		}
		assessment.assignExam(exam);
		return assessment;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_STUDENT')")
	public void saveAssessment(Assessment assessment) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
		User user = userRepository.findUserByUsername(userName);
		assessment.setUser(user);
		assessmentRepository.save(assessment);		
	}

	@Override
	public List<Assessment> getAllAssessments() {		
		return (List<Assessment>)assessmentRepository.findAll();
	}

	@Override
	public Assessment getAssessmentById(Long id) {
		return assessmentRepository.findOne(id);
	}

	@Override
	public List<Assessment> getUserAssessments(Long userId) {
		return assessmentRepository.findAssessmentsByUserId(userId);
	}
}
