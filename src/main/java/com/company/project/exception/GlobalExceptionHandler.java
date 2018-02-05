package com.company.project.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.project.core.ResponseData;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory
            .getLogger(GlobalExceptionHandler.class);
	

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseData<String> jsonException(Exception e) {
    		logger.error("发生异常了: {}", e);
        return ResponseData.fail(e.getMessage());
    }

}

