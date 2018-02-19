package edu.mum.exam.exception;

public class ExamNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 9060751397339719682L;
	
	private String examId;
	private String message = "Exam Not Found for this ID ";
	
 	public ExamNotFoundException() {}
	
	public ExamNotFoundException(String examId, String message) {
		this.examId = examId;
		if (message != null) this.message = message;		
	}
	
	public String getFullMessage() {
		return (message + examId);
	}
	
	public String getExamId() {
		return examId;
	}
	
	@Override
	public String getLocalizedMessage() {
		return super.getLocalizedMessage();
	}
}
