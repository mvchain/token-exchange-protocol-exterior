package com.mvc.sell.common.interceptor;

import com.mvc.common.context.BaseContextHandler;
import com.mvc.common.exception.auth.TokenErrorException;
import com.mvc.sell.common.annotation.NeedLogin;
import com.mvc.sell.common.exception.CheckeException;
import com.mvc.sell.constants.MessageConstants;
import com.mvc.sell.util.JwtHelper;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;

/**
 * @author qyc
 */
public class ServiceAuthRestInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(ServiceAuthRestInterceptor.class);

    @Value("${service.eurekaKey}")
    private String eurekaKey;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }

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
        Boolean isFeign = "feign".equalsIgnoreCase(request.getHeader("type")) && eurekaKey.equalsIgnoreCase(request.getHeader("eurekaKey"));
        if (null == claim && null != loginAnn && !isFeign) {
            if (uri.indexOf("/refresh") > 0 ){
                throw new LoginException(MessageConstants.getMsg("TOKEN_WRONG"));
            } else {
                throw new TokenErrorException(MessageConstants.getMsg("TOKEN_EXPIRE"), MessageConstants.TOKEN_EXPIRE_CODE);
            }
        }
        if (null != claim) {
            JwtHelper.check(claim, uri, isFeign);
        }
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
