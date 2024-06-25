package com.example.demo.Controller;


import com.example.demo.ErrorReponse.InputLimit;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.exception.NumberOverError;
import com.example.demo.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ApiController {


    private final StudentRepository studentrepository;
    private final ApiResponseSave apiResponseSave;
    @ResponseBody
    @PostMapping("/save")
    public ResponseEntity<ResponseJson> save(HttpEntity<Student> student) {
        Student s = student.getBody();


            if (Integer.parseInt(s.getGrade()) > 5) {
                log.info("error,{}", s.getGrade());

                throw new NumberOverError("5보다 큰수는안됩니다", new InputLimit(s.getGrade()), HttpStatus.BAD_REQUEST);
                //에러커컨트롤러를 정의해노서
                //자동으로 글로 처리가 이동함.
            }
            List<Student> s2 = studentrepository.savedata(s);
            s2.sort(new StudentCompare());
            return apiResponseSave.makeresponse(new Status(), new MetaData(), s2);



    }
    @ResponseBody
    @GetMapping("/all")
    public ResponseEntity<ResponseJson> all(){

        ResponseJson responseJson=new ResponseJson();
        List<Student> s2=studentrepository.getalldata();
        s2.sort(new StudentCompare());



        ApiResponseSave apiResponseSave=new ApiResponseSave();
        return apiResponseSave.makeresponse(new Status(),new MetaData(),s2);


    }

    @ResponseBody
    @GetMapping("/{grade}")
    public ResponseEntity<ResponseJson> findbygrade(@PathVariable(value="grade") String grade){

        List<Student> s=studentrepository.getallgradedata(grade);
        s.sort(new StudentCompare());

        ApiResponseSave apiResponseSave=new ApiResponseSave();
        return apiResponseSave.makeresponse(new Status(),new MetaData(),s);
    }


}
