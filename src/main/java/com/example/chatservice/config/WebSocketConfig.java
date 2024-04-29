package com.example.chatservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final static Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat")
                // .setHandshakeHandler(new PrincipalHandshakeHandler())
                .setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    // Authentication check headers
                    String authorization = accessor.getFirstNativeHeader("authorization");
                    String role = accessor.getFirstNativeHeader("role");
                    String username = accessor.getFirstNativeHeader("username");
                    var principal = new StompPrincipal(UUID.randomUUID().toString());
                    principal.addAllowedGroup(role);
                    principal.setUsername(username);
                    // Authenticate user
                    if (authorization != null && authorization.equals("Bearer token")) {
                        accessor.setUser(principal);
                        return message;
                    } else {
                        return null;
                    }
                }

                if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
                    String destination = accessor.getDestination();
                    // Kullanıcının abonelik yapma yetkisi kontrol edilir
                    if (!userHasAccessToTopic(destination, ((StompPrincipal)accessor.getUser()).getAllowedGroups())) {
                        return null;
                    }
                }

                return message;
            }
        });
    }

    private boolean userHasAccessToTopic(String topic, List<String> allowedGroups) {
        if (allowedGroups.contains("admin")) {
            return true;
        }
        // Burada veritabanı veya başka bir servis üzerinden kullanıcının erişim yetkileri kontrol edilir
        return false;
    }

    @EventListener
    public void handleSessionDisconnectEvent(SessionDisconnectEvent event) {
        StompPrincipal user = (StompPrincipal)event.getUser();
        if (user == null) {
            return;
        }
        logger.info("User disconnected: " + user.getName());
        // logger.info("Session disconnected: " + event.getSessionId());
    }
}
