package com.example.demo.response;

import org.springframework.http.HttpEntity;

import java.util.List;

public interface MakeResponse {
    public HttpEntity<ResponseJson> makeresponse(Status status, MetaData metadata, List<Student> s);
}
