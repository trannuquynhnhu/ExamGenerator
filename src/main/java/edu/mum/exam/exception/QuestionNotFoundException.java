package edu.mum.exam.exception;

public class QuestionNotFoundException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8847522876927008843L;
	private String questionId;
	private String message = "Question Not Found for this ID ";

	public QuestionNotFoundException(String questionId, String message) {
		this.questionId = questionId;
		if (message != null) this.message = message;
	}
	
	public String getQuestionId() {
		return questionId;
	}
	
	public String getFullMessage() {
		return (message + questionId);
	}

}
