package edu.mum.exam.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.exam.domain.Subject;
import edu.mum.exam.repository.SubjectRepository;
import edu.mum.exam.service.SubjectService;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService{

	@Autowired
	SubjectRepository subjectRepository;
	
	@Override
	public Iterable<Subject> getAllSubjects() {
		return subjectRepository.findAll();
	}
	public Subject getSubjectById(long id)
	{
		return subjectRepository.findOne(id);
	}

}
