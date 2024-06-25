package com.example.demo.Controller;


import com.example.demo.ErrorReponse.Data;
import com.example.demo.ErrorReponse.ErrorJson;
import com.example.demo.ErrorReponse.ErrorReponse;
import com.example.demo.ErrorReponse.InputLimit;
import com.example.demo.exception.NumberOverError;
import com.example.demo.response.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NumberOverError.class)
    public ResponseEntity<ErrorJson<InputLimit>> NumberOver(NumberOverError e){
        ErrorReponse<InputLimit> errorReponse=new ErrorReponse<>();
        Status s=new Status();
        s.setCode(String.valueOf(e.getHttpStatus().value()));
        s.setMessage(e.getMessage());
        Data<InputLimit> d=new Data<>();
        d.setRestriction(e.getData());

        return errorReponse.MakeError(s,d);
    }



}
