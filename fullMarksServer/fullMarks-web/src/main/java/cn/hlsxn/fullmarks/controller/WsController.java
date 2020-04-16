package cn.hlsxn.fullmarks.controller;

import cn.hlsxn.fullmarks.model.ChatMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class WsController {
    private static Logger log = LoggerFactory.getLogger(WsController.class);
    @Autowired
    SimpMessagingTemplate messagingTemplate;

//    public void handleChat(Authentication authentication, ChatMsg chatMsg) {
    @MessageMapping("/ws/chat")
    public void handleChat(ChatMsg chatMsg) {
        chatMsg.setDate(new Date());
        log.info("发送消息:" + chatMsg);
        messagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/chat", chatMsg);
    }

//    @MessageMapping("/ws/sendAllUser/{id}")
//    @SendTo("/topic/sendTopic/{id}")
//    public RoomMsg sendAllUser(@PathVariable(name = "id") String id, @RequestParam(name="message") RoomMsg message) {
//        System.out.println("-------------------sendAllUser==========id:"+id+"======");
//        // 也可以采用template方式
//        System.out.println("message:" + message);
//        return message;
//    }
}
