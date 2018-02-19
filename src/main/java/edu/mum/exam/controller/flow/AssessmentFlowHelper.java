package edu.mum.exam.controller.flow;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import edu.mum.exam.domain.Assessment;
import edu.mum.exam.service.AssessmentService;
import edu.mum.registration.domain.User;

@Component
public class AssessmentFlowHelper {
	
	@Autowired
	AssessmentService assessmentService;
	
	public AssessmentWrapper createAssessment(Long examId) {
		Assessment assessment = assessmentService.CreateAssessmentForExam(examId);
		assessment.setStartTime(new Date());
		return new AssessmentWrapper(assessment);
	}
	
	public void saveAssessment(AssessmentWrapper assessmentWrapper) {
		Assessment assessment = assessmentWrapper.getAssessment();
		assessment.setEndTime(new Date());
		assessmentService.saveAssessment(assessment);
	}
}
