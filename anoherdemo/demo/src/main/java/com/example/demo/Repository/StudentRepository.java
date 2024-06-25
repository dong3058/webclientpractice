package com.example.demo.Repository;


import com.example.demo.response.Student;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@Repository
public class StudentRepository {


    private final Map<String,String> repository=new HashMap<>();



    public List<Student> savedata(Student s){

       repository.put(s.getName(),s.getGrade());
       List<Student> s2=new ArrayList<>();
       s2.add(s);
       return s2;
    }


    public List<Student> getalldata(){
        List<Student> alldata=new ArrayList<>();
        repository.forEach((k,v)->{
            Student s=new Student();
            s.setName(k);
            s.setGrade(v);
            alldata.add(s);
        });


        return alldata;

    }

    public List<Student> getallgradedata(String grade){

        List<Student> alldata=new ArrayList<>();
        repository.forEach((k,v)->{
            if(v.equals(grade)){
                Student s=new Student();
                s.setName(k);
                s.setGrade(v);
                alldata.add(s);}
        });

        return alldata;
    }






}
