package cn.hlsxn.fullmarks.controller.chat;

import cn.hlsxn.fullmarks.model.User;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Component
public class PrincipalHandshakeHandler  extends  DefaultHandshakeHandler {
    // Custom class for storing principal
    @Override
    protected  Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {
        // Generate principal with UUID as name
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("----------注册姓名:"+user.getUsername());
        return new StompPrincipal(user.getUsername());
    }
}
