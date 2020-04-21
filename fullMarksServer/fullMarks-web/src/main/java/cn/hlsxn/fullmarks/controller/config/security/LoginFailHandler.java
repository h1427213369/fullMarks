package cn.hlsxn.fullmarks.controller.config.security;

import cn.hlsxn.fullmarks.model.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        //                        log.info("登录失败:" + exception);
        RespBean respBean = RespBean.error("登录失败!");
        if (exception instanceof LockedException) {
            respBean.setMsg("账户被锁定，请联系管理员!");
        } else if (exception instanceof CredentialsExpiredException) {
            respBean.setMsg("密码过期，请联系管理员!");
        } else if (exception instanceof AccountExpiredException) {
            respBean.setMsg("账户过期，请联系管理员!");
        } else if (exception instanceof DisabledException) {
            respBean.setMsg("账户被禁用，请联系管理员!");
        } else if (exception instanceof BadCredentialsException) {
            respBean.setMsg("用户名或者密码输入错误，请重新输入!");
        } else if (exception instanceof InternalAuthenticationServiceException) {
            respBean.setMsg("当前用户已登陆，请确认后重新登陆");
        }
        out.write(new ObjectMapper().writeValueAsString(respBean));
        out.flush();
        out.close();
    }
}
