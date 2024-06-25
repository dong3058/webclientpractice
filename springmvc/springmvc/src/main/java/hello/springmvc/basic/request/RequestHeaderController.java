package hello.springmvc.basic.request;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {
    //MultiValueMap 하나의 키에 여러값을 받을떄 쓰는것. 값을 꺼내면 배열골로 반환.
    @RequestMapping("/headers")
    public String headers(HttpServletRequest req, HttpServletResponse resp,
                          HttpMethod method, Locale locale,
                          @RequestHeader MultiValueMap<String, String> headermap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "mycookie", required = false) String cookie) {

        log.info("request={}", req);
        log.info("response={}", resp);
        log.info("method={}", method);
        log.info("locale={}", locale);
        log.info("host={}", host);
        log.info("cookie={}", cookie);
        return "ok";

    }

}
