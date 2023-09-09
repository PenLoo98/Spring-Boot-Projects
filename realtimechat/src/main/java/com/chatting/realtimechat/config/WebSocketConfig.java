package com.chatting.realtimechat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register a STOMP over WebSocket endpoint at the given mapping path.
        // STOMP is the Simple (or Streaming) Text Orientated Messaging Protocol.
        // The Web Socket Endpoint represents an object that can handle websocket conversations.
        // .withSockJS() : make a socket with SockJS
        registry.addEndpoint("/ws").withSockJS();
        // STOMP 를 웹소켓 채팅 객체에 매핑함.소켓은 SockJS로 만듦.
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Configure one or more prefixes to filter destinations targeting application annotated methods.
        // 클라이언트에서 메시지를 보낼 때 prefix로 "/app"를 붙여서 보내면
        // 해당 메시지들은 @MessageMapping으로 시작하는 method에 route 됩니다.
        registry.setApplicationDestinationPrefixes("/app");

        // Enable a simple message broker and configure one or more prefixes to filter destinations targeting the broker
        // Simple broker가 "/topic" 시작되는 목적지에 대해
        // subscription 및 broadcasting 을 처리하도록 설정합니다.
        registry.enableSimpleBroker("/topic");

        // 따라서, 위의 설정에서 클라이언트가 "/app/something" 주제로 메세지를 보내면
        // @MessageMapping 애노테이션이 "something"인 Controller Method 가 해당 메세지 처리 후
        // 결과 데이터를 "/topic/something" 으로 발송하게 되고
        // 이 주제(/topic/something) 로 subscribe 하고 있는 모든 클라이언트들에게 그 결과 데이터가 전달됩니다.
    }
}
