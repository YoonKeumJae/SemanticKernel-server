package com.sk.SpringSK.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.sk.SpringSK.service.KernelService;
import com.sk.SpringSK.model.AIkit;
import com.sk.SpringSK.ai.SemanticKernel;

public class SocketController extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(SocketController.class);
    private final RestTemplate restTemplate = new RestTemplate();
    private AIkit aikit;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String userMessage = message.getPayload();
        logger.info("Message from client: {}", userMessage);
        // TODO: 시맨틱 커널
        String res = KernelService.sendMsg(userMessage, this.aikit);
        System.out.println(res);
        session.sendMessage(new TextMessage(res));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("New websocket connected: {}", session.getId());
        // TODO: Kernel 객체 생성
        SemanticKernel kernel = new SemanticKernel();
        this.aikit = kernel.CreateKernel();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("Websocket disconnected: {}", session.getId());
    }
}
