package br.imd.ufrn.ai_service.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import br.imd.ufrn.ai_service.DAO.UsersDAO;

@Service
public class ChatService {
    private final ChatClient chatClient;

    ChatMemoryRepository chatMemoryRepository = new InMemoryChatMemoryRepository();
    ChatMemory chatMemory = MessageWindowChatMemory.builder()
            .chatMemoryRepository(chatMemoryRepository)
            .maxMessages(10)
            .build();

    public ChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String getDumbChatAnswer(String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    public String getChatAnswer(String prompt) {
        return chatClient.prompt()
                .advisors(
                    MessageChatMemoryAdvisor.builder(chatMemory).build()
                )
                .advisors(a ->
                    a.param(ChatMemory.CONVERSATION_ID, "usuario-1")
                )
                .user(prompt)
                .call()
                .content();
    }

    
}
