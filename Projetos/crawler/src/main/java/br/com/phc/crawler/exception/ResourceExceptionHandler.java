package br.com.phc.crawler.exception;

import java.util.Calendar;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.phc.crawler.dto.ErrorDetailsDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<String> exceptionCustomizada(CustomException e, HttpServletRequest request) {
		log.info(e.getMessage());
		
		ErrorDetailsDTO details = new ErrorDetailsDTO();
		details.setUniqueId(UUID.randomUUID().toString());
		details.setMessage(e.getMessage());
		details.setInformationCode(e.getHttpStatus().name());
		details.setStatusCode(e.getHttpStatus().value());
		details.setTime(Calendar.getInstance().getTimeInMillis());
		
		return new ResponseEntity<>(convertErrorInJson(details), e.getHttpStatus());
	}
	
	public String convertErrorInJson(ErrorDetailsDTO details) {
		try {
			return new ObjectMapper().writeValueAsString(details);
		} catch (JsonProcessingException e) {
			return e.getMessage();
		}
	}
}
