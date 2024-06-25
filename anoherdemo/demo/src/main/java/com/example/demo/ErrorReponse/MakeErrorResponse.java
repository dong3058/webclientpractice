package com.example.demo.ErrorReponse;

import com.example.demo.response.Status;
import org.springframework.http.ResponseEntity;

public interface MakeErrorResponse<T> {


    ResponseEntity<ErrorJson<T>> MakeError(Status s, Data<T> data);
}
