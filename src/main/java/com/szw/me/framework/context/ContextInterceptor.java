package com.szw.me.framework.context;

import com.szw.me.framework.util.JwtUtil;
import com.szw.me.framework.util.ResultVOUtil;
import com.szw.me.framework.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ContextInterceptor extends HandlerInterceptorAdapter {

    public static final String TOKEN_KEY = "token";

    @Autowired
    ContextInterceptorProperties contextInterceptorProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 客户端地址
        String remoteHost = request.getRemoteHost();
        // 请求接口地址
        String requestURI = request.getRequestURI();

        if (contextInterceptorProperties.getBlackList().contains(remoteHost)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().write(ResultVOUtil.error("您所在的ip已受限无法访问").toString());
            return false;
        }
        if (contextInterceptorProperties.getWhiteList().contains(requestURI)) {
            return super.preHandle(request, response, handler);
        }
        String token = getToken(request);
        ResultVO<Map<String, Object>> vo = JwtUtil.verify(token);
        if (!vo.getCode().equals(ResultVOUtil.CODE_SUCCESS)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().write(vo.toString());
            return false;
        }
        Map<String, Object> data = vo.getData();
        SzwContext context = new SzwContext();
        context.setCode((String) data.get("code"));
        context.setNick((String) data.get("nick"));
        context.setToken(token);
        SzwContext.setSzwContext(context);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        SzwContext.removeSzwContext();
    }

    private String getToken(HttpServletRequest request) {
        return request.getHeader(TOKEN_KEY);
    }
}
