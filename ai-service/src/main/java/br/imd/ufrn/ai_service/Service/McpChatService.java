package br.imd.ufrn.ai_service.Service;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.modelcontextprotocol.client.McpSyncClient;

@Service
public class McpChatService {
    private final ChatClient chatClient;

    public McpChatService(ChatClient.Builder builder, List<McpSyncClient> mcpSyncClients) {
        this.chatClient = builder.defaultTools(SyncMcpToolCallbackProvider.builder().mcpClients(mcpSyncClients).build()).build();
    }

    public String getChatAnswer(String prompt) {

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
