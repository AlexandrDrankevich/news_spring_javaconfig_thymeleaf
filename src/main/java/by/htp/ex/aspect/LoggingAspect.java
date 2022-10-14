package by.htp.ex.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger log = LogManager.getLogger();

    @AfterThrowing(pointcut = "execution(* by.htp.ex.service.impl.*.*(..))", throwing = "theExc")
    public void afterThrowingsAdvice(Throwable theExc) {
        log.error(theExc);
    }
}
