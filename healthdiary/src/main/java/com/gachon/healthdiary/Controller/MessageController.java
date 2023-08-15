package com.gachon.healthdiary.Controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Controller
@ServerEndpoint("/websocket")
public class MessageController extends Socket {
    private static final List<Session> session = new ArrayList<Session>();

    @GetMapping("/chat")
    public String chat(){
        return "chat.html";
    }

    @OnOpen
    public void open(Session newUser){
        session.add(newUser);
        // User 넘버링 1부터 시작하도록 함.
        System.out.printf("User %s connected%n", (Integer.parseInt(newUser.getId())+1) );
        System.out.println("현재 접속중인 유저 수 : " + session.size());
    }

    @OnMessage
    public void getMsg(Session recieveSession, String msg){
        for(int i=0;i<session.size();i++){
            if(!recieveSession.getId().equals(session.get(i).getId())){
                try{
//                    session.get(i).getBasicRemote().sendText("상대 : "+msg);
                    // 아놔 user받는사람 숫자가 안 맞음...
                    // 이거 숫자 어떻게 맞추냐?
                    session.get(i).getBasicRemote().sendText(String.format("User %d(상대) : %s", (Integer.parseInt(session.get(i).getId())) , msg));
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            else {
                try{
                    session.get(i).getBasicRemote().sendText("나 : "+msg);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
