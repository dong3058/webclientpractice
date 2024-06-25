package hello.embed;

import hello.servlet.HelloServlet;
import hello.spring.HelloConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class TomcatServletwithSpring {
    public static void main(String[] args) throws LifecycleException {

        Tomcat tomcat=new Tomcat();

        Connector connector=new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        //스프링 컨테이너 생성
        AnnotationConfigWebApplicationContext appContext=new AnnotationConfigWebApplicationContext();
        appContext.register(HelloConfig.class);

        DispatcherServlet dispatcher=new DispatcherServlet(appContext);

        Context context= tomcat.addContext("","/");
        tomcat.addServlet("","dispathcer",dispatcher);
        context.addServletMappingDecoded("/","dispatcher");
        tomcat.start();
    }
}
