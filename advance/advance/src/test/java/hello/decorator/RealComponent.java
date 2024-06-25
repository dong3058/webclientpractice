package hello.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealComponent implements  Component{

    @Override
    public String operation() {

        log.info("호출");
        return "data";
    }
}
