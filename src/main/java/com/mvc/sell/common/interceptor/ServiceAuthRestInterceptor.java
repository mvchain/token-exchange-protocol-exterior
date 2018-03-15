package com.mvc.sell.common.interceptor;

import com.mvc.common.context.BaseContextHandler;
import com.mvc.sell.common.annotation.NeedLogin;
import com.mvc.sell.common.exception.CheckeException;
import com.mvc.sell.constants.MessageConstants;
import com.mvc.sell.util.JwtHelper;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.List;

/**
 * @author qyc
 */
public class ServiceAuthRestInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(ServiceAuthRestInterceptor.class);

    private List<String> allowedClient;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        String token = request.getHeader("Authorization");
        BaseContextHandler.set("Authorization", token);
        Claims claim = JwtHelper.parseJWT(token);
        NeedLogin loginAnn = handlerMethod.getMethodAnnotation(NeedLogin.class);
        checkAnnotation(claim, loginAnn, request.getRequestURI(), request);
        setUserInfo(claim);
        return super.preHandle(request, response, handler);
    }

    private void checkAnnotation(Claims claim, NeedLogin loginAnn, String uri, HttpServletRequest request) throws LoginException, CheckeException {
        // check login
        if (null == claim && null != loginAnn) {
            throw new LoginException(MessageConstants.TOKEN_WRONG);
        }
        if (null != claim) {
            Boolean isFeign = "feign".equalsIgnoreCase(request.getHeader("type"));
            JwtHelper.check(claim, uri, isFeign);
        }
    }

    private String getCode(HttpServletRequest request, String key) {
        String code = request.getParameter(key);
        return code;
    }

    public void setUserInfo(Claims userInfo) {
        if (null != userInfo) {
            String username = userInfo.get("username", String.class);
            Integer userId = userInfo.get("userId", Integer.class);
            BaseContextHandler.set("username", username);
            BaseContextHandler.set("userId", BigInteger.valueOf(userId.longValue()));
        }
    }

}
