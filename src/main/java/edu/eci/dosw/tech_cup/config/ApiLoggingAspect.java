package edu.eci.dosw.tech_cup.config;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class ApiLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ApiLoggingAspect.class);

    @Around("within(edu.eci.dosw.tech_cup.controller..*) && @within(org.springframework.web.bind.annotation.RestController)")
    public Object logApiCall(ProceedingJoinPoint joinPoint) throws Throwable {
        long startedAt = System.currentTimeMillis();
        String endpoint = "N/A";
        String method = "N/A";

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            endpoint = request.getRequestURI();
            method = request.getMethod();
        }

        String operation = joinPoint.getSignature().toShortString();
        logger.info("API IN [{} {}] operation={}", method, endpoint, operation);

        try {
            Object result = joinPoint.proceed();
            long elapsedMs = System.currentTimeMillis() - startedAt;
            logger.info("API OUT [{} {}] operation={} status=OK durationMs={}", method, endpoint, operation, elapsedMs);
            return result;
        } catch (RuntimeException ex) {
            long elapsedMs = System.currentTimeMillis() - startedAt;
            logger.error("API ERROR [{} {}] operation={} durationMs={} message={}", method, endpoint, operation,
                    elapsedMs, ex.getMessage(), ex);
            throw ex;
        }
    }
}
