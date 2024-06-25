package hello.advance;


import hello.advance.trace.logtrace.FieldLogTrace;
import hello.advance.trace.logtrace.LogTrace;
import hello.advance.trace.logtrace.ThreadLocalTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalTrace();
    }

}
