package com.mynote.sys.aspect;

import com.mynote.sys.annotation.LogAfterExecution;
import com.mynote.base.constant.Enum.EventTypeEnum;
import com.mynote.sys.service.EventLogsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhishubin
 * @date 2023/12/19 11:55
 * @description
 */
@Aspect
@Component
public class LogAfterExecutionAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EventLogsService eventLogsService;

    @Pointcut("@annotation(log)")
    public void logAfterExecution(LogAfterExecution log) {
    }

    @AfterReturning(pointcut = "logAfterExecution(log)", returning = "result")
    public void log(JoinPoint joinPoint, LogAfterExecution log, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        String logMessage = "Method executed: " + className + "." + methodName;

        // 获取注解中的自定义类参数
        EventTypeEnum eventType = log.eventType();
        logMessage += ". Event Type: " + eventType.getType();

        // 如果有返回值，也可以在日志中记录返回值
        if (result != null) {
            logMessage += ". Result: " + result.toString();
        }

        //eventLogsService.save()

        logger.info(logMessage);
    }
}

