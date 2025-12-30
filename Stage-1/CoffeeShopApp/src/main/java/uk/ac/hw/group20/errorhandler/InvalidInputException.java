package uk.ac.hw.group20.errorhandler;

public class InvalidInputException extends RuntimeException{

	public InvalidInputException(String message) {
		super(message);
	}
}
