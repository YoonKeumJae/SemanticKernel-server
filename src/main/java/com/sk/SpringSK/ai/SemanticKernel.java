package com.sk.SpringSK.ai;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.aiservices.openai.chatcompletion.OpenAIChatCompletion;
import com.microsoft.semantickernel.services.chatcompletion.ChatCompletionService;
import com.microsoft.semantickernel.services.chatcompletion.ChatHistory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.sk.SpringSK.model.AIkit;

public class SemanticKernel {

    private static String getApiKey() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(input);
            return properties.getProperty("GITHUB_TOKEN");
        } catch (IOException e) {
            throw new RuntimeException("설정 파일을 읽을 수 없습니다.", e);
        }
    }

    public AIkit CreateKernel() {
        String key = getApiKey();
        String endpoint = "https://models.inference.ai.azure.com";
        String model = "gpt-4o";
        // client 생성
        OpenAIAsyncClient client = new OpenAIClientBuilder()
                .credential(new AzureKeyCredential(key))
                .endpoint(endpoint)
                .buildAsyncClient();
        // service 생성
        ChatCompletionService service = OpenAIChatCompletion.builder()
                .withOpenAIAsyncClient(client)
                .withModelId(model)
                .build();
        // kernel 생성
        Kernel kernel = Kernel.builder().withAIService(ChatCompletionService.class, service).build();
        // history 생성
        ChatHistory history = new ChatHistory();
        return new AIkit(kernel, history, service);
    }
}