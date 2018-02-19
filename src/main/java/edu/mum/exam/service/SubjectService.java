package edu.mum.exam.service;

import edu.mum.exam.domain.Subject;

public interface SubjectService {
	Iterable<Subject> getAllSubjects();
	Subject getSubjectById(long id);
}
