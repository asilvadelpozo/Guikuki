package com.guikuki.aspects;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.omg.PortableInterceptor.RequestInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Aspect in charge of logging.
 * Created by antoniosilvadelpozo on 12/04/14.
 */
@Aspect
public class Logging {

    private final String SEPARATOR = " -> ";
    private final String SERVICE_COMPONENT = "Service";
    private final String DAO_COMPONENT = "DAO";
    private final String CONTROLLER_COMPONENT = "Controller";

    private final static Logger LOGGER = Logger.getLogger(Logging.class);

    /**
     * Logs DAO methods.
     * @param proceedingJoinPoint
     * @return Object that ProceedingJoinPoint returns.
     * @throws Throwable
     */
    @Around("execution(public * com.guikuki.persistence.dao..*(..))")
    public Object logDAOMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return logMethod(proceedingJoinPoint, DAO_COMPONENT);
    }

    /**
     * Logs service methods.
     * @param proceedingJoinPoint
     * @return Object that ProceedingJoinPoint returns.
     * @throws Throwable
     */
    @Around("execution(public * com.guikuki.service..*(..))")
    public Object logServiceMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return logMethod(proceedingJoinPoint, SERVICE_COMPONENT);
    }

    /**
     * Logs controller methods.
     * @param proceedingJoinPoint
     * @return Object that ProceedingJoinPoint returns.
     * @throws Throwable
     */
    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object logControllerMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return logMethod(proceedingJoinPoint, CONTROLLER_COMPONENT);
    }

    /**
     * Logs the method represented by the ProceedingJoinPoint.
     * @param proceedingJoinPoint
     * @param component: component prefix to by used in the traces.
     * @return: Object that ProceedingJoinPoint returns.
     * @throws Throwable
     */
    private Object logMethod(ProceedingJoinPoint proceedingJoinPoint, String component) throws Throwable {
        Object object;
        Long startTime = System.currentTimeMillis();

        LOGGER.info(proceedingJoinPoint.getSignature().getDeclaringType().getCanonicalName() + SEPARATOR + "Start " + component + " Method: " + proceedingJoinPoint.getSignature().getName());

        logArguments(proceedingJoinPoint);

        try {
            object = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            logException(proceedingJoinPoint, e);
            throw e;
        }

        logReturnObjectAndResponseTime(proceedingJoinPoint, object, startTime);

        LOGGER.info(proceedingJoinPoint.getSignature().getDeclaringType().getCanonicalName() + SEPARATOR + "End " + component + " Method: " + proceedingJoinPoint.getSignature().getName());
        return object;
    }

    /**
     * Logs return object and response time is debug level is enabled.
     * @param proceedingJoinPoint
     * @param object
     * @param startTime
     */
    private void logReturnObjectAndResponseTime(ProceedingJoinPoint proceedingJoinPoint, Object object, Long startTime) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(proceedingJoinPoint.getSignature().getDeclaringType().getCanonicalName() + SEPARATOR + "Returns: " + object);
            LOGGER.debug(proceedingJoinPoint.getSignature().getDeclaringType().getCanonicalName() + SEPARATOR + "Response Time: " + (System.currentTimeMillis() - startTime) + " ms");
        }
    }

    /**
     * Logs the arguments of the method.
     * @param proceedingJoinPoint
     */
    private void logArguments(ProceedingJoinPoint proceedingJoinPoint) {
        StringBuffer argLog = new StringBuffer();
        for (Object arg : proceedingJoinPoint.getArgs()) {
            argLog.append(arg);
            argLog.append(",");
        }
        removeLastComa(argLog);
        LOGGER.info(proceedingJoinPoint.getSignature().getDeclaringType().getCanonicalName() + SEPARATOR + "Args: " + argLog);
    }

    /**
     * Logs an exception stack trace.
     * @param proceedingJoinPoint
     * @param e: exception to be logged.
     */
    private void logException(ProceedingJoinPoint proceedingJoinPoint, Exception e) {
        LOGGER.error(proceedingJoinPoint.getSignature().getDeclaringType().getCanonicalName() + SEPARATOR + "Exception: " + e.getMessage());
        LOGGER.error(proceedingJoinPoint.getSignature().getDeclaringType().getCanonicalName() + SEPARATOR + "Stack Trace: " +ExceptionUtils.getStackTrace(e));
    }

    /**
     * Removes the last coma in case that this exists.
     * @param argLog: arguments to be logged.
     */
    private void removeLastComa(StringBuffer argLog) {
        if(argLog != null && argLog.length() > 0) {
            argLog.deleteCharAt(argLog.length() - 1);
        }
    }

}
