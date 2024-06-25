package hello.advance.proxy.v_4postprocessor.post_processor;

import lombok.RequiredArgsConstructor;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@RequiredArgsConstructor
public class LogTracePostprocessor implements BeanPostProcessor {

    private final String packagename;
    private final Advisor advisor;


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        String packge=bean.getClass().getPackageName();
        if(!packge.startsWith(packagename)){
            return bean;
        }

        ProxyFactory proxyFactory=new ProxyFactory(bean);
        proxyFactory.addAdvisor(advisor);
        return proxyFactory.getProxy();
    }
}
