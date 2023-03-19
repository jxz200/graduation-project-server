package com.example.windserver.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.windserver.common.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;


public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object employee = session.getAttribute("employee");

        if (employee == null) {
            response.getWriter().write(JSON.toJSONString(R.error("Employee is not logged in")));
            return false;
        }

        // 用户已登录，继续处理请求
        return true;
    }
}
