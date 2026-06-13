package br.imd.ufrn.ai_service.Service;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import br.imd.ufrn.ai_service.DAO.UsersDAO;

@Service
public class RagUserService {
    private final UsersDAO usersDAO;
    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public RagUserService(UsersDAO usersDAO, ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        this.usersDAO = usersDAO;
        this.chatClient = chatClientBuilder.build();
        this.vectorStore = vectorStore;
    }

    public String getChatAnswer(String prompt) {
        return chatClient.prompt()
        .advisors(
            QuestionAnswerAdvisor
                .builder(vectorStore)
                .build()
        )
        .user(prompt).call().content();
    }

    public void saveUsers(List<String> users) {
        usersDAO.add(users);
    }

    public List<String> findClosestMatches(String query) {
        return usersDAO.findClosestMatches(query, 5);
    }

    public String findClosestMatch(String query) {
        return usersDAO.findClosestMatch(query);
    }
    
}
