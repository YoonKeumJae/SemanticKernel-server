package com.sk.SpringSK.service;

import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.services.chatcompletion.ChatCompletionService;
import com.microsoft.semantickernel.services.chatcompletion.ChatHistory;
import com.microsoft.semantickernel.services.chatcompletion.ChatMessageContent;
import com.sk.SpringSK.model.AIkit;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KernelService {

    public static String sendMsg(String userMessage, AIkit kit){
        Kernel kernel = kit.getKernel();
        ChatHistory history = kit.getHistory();
        ChatCompletionService service = kit.getService();
        history.addUserMessage(userMessage);
        List<ChatMessageContent<?>> responses = service.getChatMessageContentsAsync(history, kernel, null).block();
        StringBuilder res = new StringBuilder();
        if (responses != null && !responses.isEmpty()) {
            for (ChatMessageContent<?> response : responses) {
                System.out.println("AI 응답: " + response.getContent());
                res.append(response.getContent());
                history.addAssistantMessage(response.getContent());
            }
        }
        return res.toString();
    }
}
