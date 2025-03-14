package com.sk.SpringSK.model;
import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.services.chatcompletion.ChatCompletionService;
import com.microsoft.semantickernel.services.chatcompletion.ChatHistory;

public class AIkit {
    private final Kernel kernel;
    private final ChatHistory history;
    private final ChatCompletionService service;
    public AIkit(Kernel kernel, ChatHistory history, ChatCompletionService service) {
        this.kernel = kernel;
        this.history = history;
        this.service = service;
    }
    public Kernel getKernel() {
        return kernel;
    }
    public ChatHistory getHistory() {
        return history;
    }
    public ChatCompletionService getService() {
        return service;
    }
}
