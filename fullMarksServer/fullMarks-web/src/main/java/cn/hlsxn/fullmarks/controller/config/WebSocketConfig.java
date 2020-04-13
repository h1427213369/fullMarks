package cn.hlsxn.fullmarks.controller.config;


import cn.hlsxn.fullmarks.controller.chat.PrincipalHandshakeHandler;
import cn.hlsxn.fullmarks.controller.chat.WebSocketDecoratorFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

/**
 *
 import org.springframework.context.annotation.Configuration;
 import org.springframework.messaging.simp.config.MessageBrokerRegistry;
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Autowired
    private WebSocketDecoratorFactory webSocketDecoratorFactory;
    @Autowired
    private PrincipalHandshakeHandler principalHandshakeHandler;

    private static Logger log = Logger.getLogger(WebSocketConfig.class);
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        log.info("---------进入成功");
        registry.addEndpoint("/ws/ep")
                .setAllowedOrigins("*")
                .setHandshakeHandler(principalHandshakeHandler)
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue","/topic");
//        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.addDecoratorFactory(webSocketDecoratorFactory);
        super.configureWebSocketTransport(registration);
    }
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
//        log.info("---------进入成功");
//        stompEndpointRegistry.addEndpoint("/ws/endpointChat").setAllowedOrigins("*").withSockJS();
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.enableSimpleBroker("/queue","/topic");
//    }
}
