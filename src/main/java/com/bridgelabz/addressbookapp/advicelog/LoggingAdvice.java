package com.bridgelabz.addressbookapp.advicelog;
/**
 * @Purpose: Logger Object is Used to log messages for a specific system or application component
 * @author: Vamsi Krishna
 * @since: 15/12/2021
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {
    Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut(value = "execution(* com.bridgelabz.addressbookapp .*.*.*(..) )")
    public void myPointCut() {

    }

    /**
     * @Purpose: this method is used for action taken by an aspect at a particular join.
     * @param proceedingJoinPoint the code execution jumps to next advice or to the target method
     * @return object of proceedingJoinPoint.proceed() method
     * @throws Throwable
     */
    @Around("myPointCut()")
    public Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        Object[] array = proceedingJoinPoint.getArgs();
        log.info("method invokes " + className + " : " + methodName + "()" + "argument : "
                + mapper.writeValueAsString(array));
        Object object = proceedingJoinPoint.proceed();
        log.info(className + " : " + methodName + "()" + "Response : " + mapper.writeValueAsString(object));
        return object;
    }

}
