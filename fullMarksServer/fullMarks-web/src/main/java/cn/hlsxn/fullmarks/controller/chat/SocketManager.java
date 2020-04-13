package cn.hlsxn.fullmarks.controller.chat;

import org.apache.log4j.Logger;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;

/**
 * socket管理器
 */
public class SocketManager {
    private static Logger log = Logger.getLogger(SocketManager.class);
    private static ConcurrentHashMap<String, WebSocketSession> manager = new ConcurrentHashMap<String, WebSocketSession>();

    public static void add(String key, WebSocketSession webSocketSession) {
        log.info("新添加webSocket连接 {} " + key);
        System.out.println("新添加webSocket连接 {} " + key);
        manager.put(key, webSocketSession);
    }

    public static void remove(String key) {
        log.info("移除webSocket连接 {} " + key);
        manager.remove(key);
    }

    public static WebSocketSession get(String key) {
        log.info("获取webSocket连接 {}" + key);
        System.out.println("获取webSocket连接 {}" + key);
        return manager.get(key);
    }


}