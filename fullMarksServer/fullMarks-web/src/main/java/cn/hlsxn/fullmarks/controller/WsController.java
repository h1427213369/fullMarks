package cn.hlsxn.fullmarks.controller;

import cn.hlsxn.fullmarks.model.ChatMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class WsController {
//    private static Logger log = Logger.getLogger(WsController.class);
    @Autowired
    SimpMessagingTemplate messagingTemplate;

//    public void handleChat(Authentication authentication, ChatMsg chatMsg) {
    @MessageMapping("/ws/chat")
    public void handleChat(ChatMsg chatMsg) {
        chatMsg.setDate(new Date());
        System.out.println("****************进入："+chatMsg);

        messagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/chat", chatMsg);
    }

    @MessageMapping("/ws/sendAllUser/{id}")
    @SendTo("/topic/sendTopic/{id}")
    public String sendAllUser(@PathVariable(name = "id") String id, @RequestParam(name="message") String message) {
        System.out.println("-------------------sendAllUser==========id:"+id+"======");
        // 也可以采用template方式
        System.out.println("message:" + message);
        return message;
    }
}
