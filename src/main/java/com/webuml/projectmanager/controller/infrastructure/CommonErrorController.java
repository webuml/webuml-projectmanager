package com.webuml.projectmanager.controller.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

public abstract class CommonErrorController {

  private Logger logger = LoggerFactory.getLogger(CommonErrorController.class);

  @ExceptionHandler({NoSuchElementException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public void notFound() {
  }

  @ExceptionHandler//({IllegalArgumentException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public void badRequest(HttpMessageNotReadableException e) {
    logger.error(e.getMessage());
    e.printStackTrace();
  }

  @ExceptionHandler({IllegalStateException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public void internalServerError() {
  }
}
