package hello.aop;

import hello.aop.exam.ExamService;
import hello.aop.exam.aspect.ReTryAspect;
import hello.aop.exam.aspect.TraceAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Slf4j
@Import({TraceAspect.class, ReTryAspect.class})
public class examtest {

    @Autowired
    ExamService examService;

    @Test
    void test(){
       for(int i=0;i<5;i++){
           log.info("client requst i={}",i);
           examService.request("data"+i);
       }
    }
}
