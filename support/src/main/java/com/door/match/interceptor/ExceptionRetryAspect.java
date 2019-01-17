/**
 * FileName: ExceptionRetryAspect
 * Author:   JackMore
 * Date:     2019/1/14 20:56
 * Description:
 */
package com.door.match.interceptor;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author JackMore
 * @create 2019/1/14
 * @since 1.0.0
 */

import com.alibaba.fastjson.JSONObject;
import com.door.match.annotations.ExceptionRetry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * <异常重试切面>
 *
 * @author lihaitao on 2019/1/2
 */
@Slf4j
@Aspect
@Component
public class ExceptionRetryAspect {

    @Pointcut("@annotation(com.door.match.annotations.ExceptionRetry)")
    public void retryPointCut() {
    }

    @Around("retryPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ExceptionRetry retry = method.getAnnotation(ExceptionRetry.class);
        String name = method.getName();
        Object[] args = joinPoint.getArgs();
        String uuid = UUID.randomUUID().toString();
        log.info("执行重试切面{}, 方法名称{}, 方法参数{}", uuid, name, JSONObject.toJSONString(args));
        int times = retry.times();
        long waitTime = retry.waitTime();
        Class[] needThrowExceptions = retry.needThrowExceptions();
        Class[] catchExceptions = retry.catchExceptions();
        // check param
        if (times <= 0) {
            times = 1;
        }

        for (; times >= 0; times--) {
            try {
                return joinPoint.proceed();
            } catch (Exception e) {
                // 如果需要抛出的异常不是空的, 看看是否需要抛出
                if (needThrowExceptions.length > 0) {
                    for (Class exception : needThrowExceptions) {
                        if (exception == e.getClass()) {
                            log.warn("执行重试切面{}失败, 异常在需要抛出的范围{}, 业务抛出的异常类型{}", uuid, needThrowExceptions, e.getClass().getName());
                            throw e;
                        }
                    }
                }

                // 如果需要抛出异常，而且需要捕获的异常为空那就需要再抛出
                if (catchExceptions.length > 0) {
                    boolean needCatch = false;
                    for (Class catchException : catchExceptions) {
                        if (e.getClass() == catchException) {
                            needCatch = true;
                            break;
                        }
                    }
                    if (!needCatch) {
                        log.warn("执行重试切面{}失败, 异常不在需要捕获的范围内, 需要捕获的异常{}, 业务抛出的异常类型{}", uuid, catchExceptions, e.getClass().getName());
                        throw e;
                    }
                }

                // 如果接下来没有重试机会的话，直接报错
                if (times <= 0) {
                    log.warn("执行重试切面{}失败", uuid);
                    throw e;
                }

                // 休眠 等待下次执行
                if (waitTime > 0) {
                    Thread.sleep(waitTime);
                }

                log.warn("执行重试切面{}, 还有{}次重试机会, 异常类型{}, 异常信息{}, 栈信息{}", uuid, times, e.getClass().getName(), e.getMessage(), e.getStackTrace());
            }
        }
        return false;
    }

}