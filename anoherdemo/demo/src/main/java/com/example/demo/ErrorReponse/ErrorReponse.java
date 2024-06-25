package com.example.demo.ErrorReponse;

import com.example.demo.response.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorReponse<T> implements MakeErrorResponse<T> {

    @Override
    public ResponseEntity<ErrorJson<T>> MakeError(Status s,Data<T> data) {

        ErrorJson<T> errorJson=new ErrorJson<>();
        errorJson.setData(data);
        errorJson.setStatus(s);
        return new ResponseEntity<>(errorJson, HttpStatus.BAD_REQUEST);
    }
}
