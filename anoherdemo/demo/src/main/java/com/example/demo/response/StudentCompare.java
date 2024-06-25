package com.example.demo.response;

import com.example.demo.response.Student;

import java.util.Comparator;

public class StudentCompare implements Comparator<Student> {


    @Override
    public int compare(Student o1, Student o2) {

        Integer o1_grade=Integer.parseInt(o1.getGrade());
        Integer o2_grade=Integer.parseInt(o2.getGrade());
        if(o1_grade==o2_grade){

            return o1.getName().compareTo(o2.getName());
        }
        else{
            if (o1_grade>o2_grade){
                return 1;
            }
            else{
                return -1;
            }
        }

    }
}
