package rs.ursus.commons.spring.logging.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import rs.ursus.commons.spring.logging.annotations.Log;

import java.lang.annotation.Annotation;

import static org.springframework.util.ReflectionUtils.*;

/**
 * Created by nighthawk on 5/7/16.
 */
@Component
public class LoggerBeanPostProcessor implements BeanPostProcessor {

    private static final Class<? extends Annotation> ANNOTATED_WITH = Log.class;

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class cls = bean.getClass();
        doWithFields(cls, field -> {
            makeAccessible(field);
            setField(field, bean, LoggerFactory.getLogger(cls));
        }, field -> field.isAnnotationPresent(ANNOTATED_WITH) && Logger.class.isAssignableFrom(field.getType()));
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
