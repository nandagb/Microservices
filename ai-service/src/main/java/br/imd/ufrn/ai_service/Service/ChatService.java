package br.imd.ufrn.ai_service.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import br.imd.ufrn.ai_service.DAO.UsersDAO;

@Service
public class ChatService {
    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String getChatAnswer(String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    
}
