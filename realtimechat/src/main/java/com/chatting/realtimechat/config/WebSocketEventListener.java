package com.chatting.realtimechat.config;

import com.chatting.realtimechat.Entity.ChatMessage;
import com.chatting.realtimechat.Entity.MessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

// 세션에서 나간 사용자를 인식하고 메시지를 출력하도록 함.
@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageTemplate;

    @EventListener
    public void handleWebSocketDisconnectListener(
            // 세션 연결해제 이벤트가 발생할 때 호출된다.
            SessionDisconnectEvent disconnectEvent
    ){
        // STOMP 메시지 헤더에 접근하기 위한 객체를 생성
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(disconnectEvent.getMessage());

        // 연결해제된 세션에서 세션속성값 username을 가져온다.
        String username = (String)headerAccessor.getSessionAttributes().get("username");

        // 가져오려는 username이 없다면
        if (username != null){
            log.info("User disconnected: {}", username);
            var chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();
            messageTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
