package br.imd.ufrn.ai_service.Embedding;

import java.util.List;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.imd.ufrn.ai_service.Context.UserData;

@RestController
public class EmbeddingController {
    private final MemoryEmbeddingService memoryEmbeddingService;
    private final EmbeddingService embeddingService;

    public EmbeddingController(MemoryEmbeddingService memoryEmbeddingService, EmbeddingService embeddingService) {
        this.memoryEmbeddingService = memoryEmbeddingService;
        this.embeddingService = embeddingService;
    }

    @GetMapping("ufrn")
    public String getEmbbedAnswer(@RequestParam String prompt) {
        return memoryEmbeddingService.findCurso(prompt);
    }

    @GetMapping("embedding")
    public String getAnswer(@RequestParam String prompt) {
        return embeddingService.findClosestMatch(prompt);
    }

    @GetMapping("addcurso")
    public void addItem(@RequestParam String prompt) {
        List<String> cursos = List.of(prompt);
        embeddingService.save(cursos);
    }

    ///Minha lógica
    @GetMapping("ollama-chat")
    public String getChatAnswer(@RequestParam String prompt) {
        System.out.println("Testando o Ollama chat service");
        return memoryEmbeddingService.getChatAnswer(prompt);
    }

    @GetMapping("add-users")
    public void addUsers() {
        embeddingService.save(UserData.USERS);
    }

    @GetMapping("users")
    public String getEmbbedUserAnswer(@RequestParam String prompt) {
        return embeddingService.findClosestMatch(prompt);
    }
}
