package edu.mum.exam.exception;

public class ImageNotSaveException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4335525189191891257L;
	
	
	private String questionId;
	private String message = "Image cannot be saved for this question ID ";
	
 	public ImageNotSaveException() {}
	
	public ImageNotSaveException(String questionId, String message) {
		this.questionId = questionId;

		if (message != null && !message.isEmpty()) this.message = message;
		
	}
	
	public String getFullMessage() {
		return (message + questionId);
	}
	
	public String getEployeeId() {
		return questionId;
	}
	
	@Override
	public String getLocalizedMessage() {		
		return super.getLocalizedMessage();
	}

}
