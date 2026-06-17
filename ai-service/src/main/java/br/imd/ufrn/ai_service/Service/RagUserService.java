package br.imd.ufrn.ai_service.Service;

import java.util.List;

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
import br.imd.ufrn.ai_service.Model.SearchResult;

@Service
public class RagUserService {
    private final UsersDAO usersDAO;
    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    ChatMemoryRepository chatMemoryRepository = new InMemoryChatMemoryRepository();
    ChatMemory chatMemory = MessageWindowChatMemory.builder()
            .chatMemoryRepository(chatMemoryRepository)
            .maxMessages(10)
            .build();

    public RagUserService(UsersDAO usersDAO, ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        this.usersDAO = usersDAO;
        this.chatClient = chatClientBuilder.build();
        this.vectorStore = vectorStore;
    }

    public SearchResult getDetailsChatAnswer(String prompt) {
        String closestMatch = usersDAO.findClosestMatch(prompt);
        String answer = chatClient.prompt().user(closestMatch+prompt).call().content();
        return new SearchResult(prompt, answer, closestMatch);
    }

    public String getChatAnswer(String prompt) {
        return chatClient.prompt()
        .advisors(
            QuestionAnswerAdvisor.builder(vectorStore).build(),
            MessageChatMemoryAdvisor.builder(chatMemory).build()
        )
        .advisors(a ->
            a.param(ChatMemory.CONVERSATION_ID, "usuario-1")
        )
        .user(prompt).call().content();
    }

    public String getDumbChatAnswer(String prompt) {
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
