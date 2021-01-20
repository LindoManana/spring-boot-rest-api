package io.javatricks.springboot.restapi.exception.handler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

	@Autowired
	private ApiErrorResponse apiErrorResponse;

	/**
	 * Triggered when a 'required' request parameter is missing.
	 **/

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		// print exception
		this.exceptionLogger(ex);

		apiErrorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,
				ex.getMessage());

		return buildResponseEntity(apiErrorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Triggered when the request format is not supported.
	 **/

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		// print exception
		this.exceptionLogger(ex);

		StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

		apiErrorResponse = new ApiErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
				HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2));

		return buildResponseEntity(apiErrorResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	/**
	 * Triggered when an object fails @Valid validation.
	 **/

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		// print exception
		this.exceptionLogger(ex);

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		apiErrorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, errors.get(0));

		return buildResponseEntity(apiErrorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Triggered when request JSON is malformed.
	 **/

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		// print exception
		this.exceptionLogger(ex);

		String errorMessage = "Request body is either malformed or incorrect.";
		apiErrorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, errorMessage);

		return buildResponseEntity(apiErrorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

		// print exception
		this.exceptionLogger(ex);

		apiErrorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, ex.getMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> badRequestException(ResourceNotFoundException ex, WebRequest request) {

		// print exception
		this.exceptionLogger(ex);

		apiErrorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,
				ex.getMessage());
		return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiErrorResponse apiErrorResponse, HttpStatus httpStatus) {

		Map<String, ApiErrorResponse> error = new LinkedHashMap<>();

		error.put("error", apiErrorResponse);

		return new ResponseEntity<>(error, httpStatus);
	}

	private <T> void exceptionLogger(T ex) {
		for (StackTraceElement ste : ((Throwable) ex).getStackTrace()) {
			LOGGER.error("@@@ error ==> " + ste);
		}
	}

}
