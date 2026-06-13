package br.imd.ufrn.ai_service.DAO;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Repository;

@Repository
public class UsersDAO {
    private final VectorStore vectorStore;

    public UsersDAO(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void add(List<String> users) {
        List<Document> documents = users.stream()
            .map(Document::new)
            .toList();
        vectorStore.add(documents);
    }

    public List<String> findClosestMatches(String query,int numberOfMatches) {
        SearchRequest request = SearchRequest.builder()
            .query(query)
            .topK(numberOfMatches)
            .build();
        List<Document> results = vectorStore.similaritySearch(request);
        if (results == null) {
            return List.of();
        }
        return results.stream()
            .map((Document doc) -> doc.getText())
            .toList();
    }

    public String findClosestMatch(String query) {
        return findClosestMatches(query, 1).get(0);            
    }
}
