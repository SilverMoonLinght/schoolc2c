package com.schoolc2c.interceptors;

import com.schoolc2c.annotations.LoginRequired;
import com.schoolc2c.annotations.PassToken;
import com.schoolc2c.util.HttpclientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.URLEncoder;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter{


public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
    response.setHeader("Access-Control-Allow-Headers", " Origin, X-Requested-With, content-Type, Accept, Authorization,token");
    response.setHeader("Access-Control-Max-Age","3600");

    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

    String token = request.getHeader("token");

    if (!(handler instanceof HandlerMethod)){
        return true;
    }

    HandlerMethod handlerMethod = (HandlerMethod)handler;
    Method method = handlerMethod.getMethod();
    if (method.isAnnotationPresent(PassToken.class)){
        PassToken passToken = method.getAnnotation(PassToken.class);
        if (passToken.required()){
            return true;
        }
    }


    if (method.isAnnotationPresent(LoginRequired.class)){
        LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
        if (loginRequired.loginSuccess()){
            if (StringUtils.isBlank(token)){
                response.sendRedirect("http://127.0.0.1:8083/nullToken");
            }else {
                String success = HttpclientUtil.doGet("http://127.0.0.1:8083/verifyToken?token="+ URLEncoder.encode(token,"UTF-8"));
                if (!success.equals("success")) {
                    response.sendRedirect("http://127.0.0.1:8083/nullToken");
                }
            }

        }
    }


    return true;
}
}
