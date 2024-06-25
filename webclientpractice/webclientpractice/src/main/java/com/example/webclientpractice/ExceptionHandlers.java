package com.example.webclientpractice;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ControllerAdvice
@Slf4j
public class ExceptionHandlers {




    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> ex(Exception e){
        log.info("exceptionclass:{}",e.getClass());
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }


    @ExceptionHandler(WebClientResponseException.BadRequest.class)
    public ResponseEntity<String> handleBadRequest(WebClientResponseException.BadRequest ex) {

        log.info("check");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Bad request: " + ex.getMessage());
    }

}
