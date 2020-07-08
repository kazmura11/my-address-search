package com.example.myAddressSearch.handlers.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.myAddressSearch.models.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class MyRestExceptionHandler extends ResponseEntityExceptionHandler {
	// こっちかなーと思ったら違う様子…handleBindExceptionが正解
	// @Override
	// protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	//     log.info("handleMethodArgumentNotValid");
	//     return super.handleExceptionInternal(ex, "MethodArgumentNotValidException", null, HttpStatus.INTERNAL_SERVER_ERROR, request);
	// }

	/**
	 * Restのエラーメッセージを返却
	 * ajaxからはxhrのエラーで取得できる
	 * 例えば、response.data[n] / xhr.responseJSON[n]等で取得できる
	 */
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		log.info("handleBindException");
		List<ErrorResponse> errors = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			String key = "global";
			String message = error.getDefaultMessage();
			errors.add(new ErrorResponse(key, message));
		}
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			String key = error.getField();
			String message = error.getDefaultMessage();
			errors.add(new ErrorResponse(key, message));
		}
		return super.handleExceptionInternal(ex, errors, headers, status, request);
	}

	/**
	 * Restのすべての例外を処理する
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		log.info("handleAllException");
		return super.handleExceptionInternal(ex, "handleAllException", null, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}
