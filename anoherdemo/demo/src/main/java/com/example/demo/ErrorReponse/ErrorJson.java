package com.example.demo.ErrorReponse;

import com.example.demo.response.Status;
import org.springframework.http.ResponseEntity;

@lombok.Data
public class ErrorJson<T> {


    private Status status;
    private Data<T> data;


}
