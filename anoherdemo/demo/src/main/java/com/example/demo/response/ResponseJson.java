package com.example.demo.response;


import lombok.Data;

import java.util.List;

@Data
public class ResponseJson {

    private Status status;
    private MetaData metaData;
    private List<Student> result;

}
