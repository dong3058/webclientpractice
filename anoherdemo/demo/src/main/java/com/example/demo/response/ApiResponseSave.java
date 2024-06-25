package com.example.demo.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ApiResponseSave implements MakeResponse {

    private final  ResponseJson responseJson=new ResponseJson();
    @Override
    public ResponseEntity<ResponseJson> makeresponse(Status status,MetaData metadata,List<Student> s) {

        status.setCode(String.valueOf(HttpStatus.OK.value()));
        status.setMessage("ok");

        metadata.setResultCount(String.valueOf(s.size()));


        responseJson.setStatus(status);
        responseJson.setResult(s);
        responseJson.setMetaData(metadata);
        HttpHeaders headers=new HttpHeaders();

        return new ResponseEntity<>(responseJson,headers,HttpStatus.OK);
    }
}
