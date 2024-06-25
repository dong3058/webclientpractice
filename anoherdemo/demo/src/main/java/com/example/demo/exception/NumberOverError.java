package com.example.demo.exception;


import com.example.demo.ErrorReponse.InputLimit;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//ResponseStatus(code=HttpStatus.NOT_FOUND,reason="숫자가 너무커욧!")
@Data
public class NumberOverError extends RuntimeException{

    private HttpStatus httpStatus;

    private InputLimit data;

    public NumberOverError(String message,InputLimit data,HttpStatus httpStatus){
        super(message);
        this.httpStatus=httpStatus;
        this.data=data;
    }



}
