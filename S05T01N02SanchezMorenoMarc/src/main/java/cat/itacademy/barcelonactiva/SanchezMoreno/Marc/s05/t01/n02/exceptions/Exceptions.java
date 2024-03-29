package cat.itacademy.barcelonactiva.SanchezMoreno.Marc.s05.t01.n02.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class Exceptions {
	
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(EntityNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("flower not found with this id");
	}

	
	@ExceptionHandler(HttpMessageConversionException.class)
	public ResponseEntity<String> handleInvalidInput(HttpMessageConversionException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect data type");
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("unexpected error\n" + ex.getMessage());
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<String> handleNoFlowersFound(EmptyResultDataAccessException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("No flowers found in our database. Reason: " + ex.getMessage());
	}
}
