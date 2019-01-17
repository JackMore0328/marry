package com.door.match.interceptor;

import com.door.match.annotations.ValidToken;
import com.door.match.base.Constants;
import com.door.match.base.ResultDto;
import com.door.match.exception.BasicException;
import com.door.match.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    public final static String REDIS_TOKENS_PREFIX = "crowdfunding:admin:tokens:";
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();


        ValidToken validToken = method.getAnnotation(ValidToken.class);
        // 有 @ValidToken 注解，需要token认证
        if (validToken != null) {
            try {
                String authorization = request.getHeader("token");
                if (authorization == null || !redisUtil.exists(Constants.SYSPREFIX + authorization)) {
                    throw new BasicException(ResultDto.CODE_AUTH_ERROR, "token失效或丢失");
                }
            } catch (BasicException e) {
                throw new BasicException(ResultDto.CODE_AUTH_ERROR, "token失效或丢失");
            }
        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
