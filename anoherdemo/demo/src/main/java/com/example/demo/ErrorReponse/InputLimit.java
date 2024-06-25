package com.example.demo.ErrorReponse;

import lombok.Data;

@Data
public class InputLimit {


    private String currentgrade;

    // 객체를 json으로 변활할떄 객체안의 값에대한 접근이 일어나는대
    //private이면 get으로 꺼내야하므로 get을 정의해주자.
    public InputLimit(String currentgrade){
        this.currentgrade=currentgrade;
    }



}
