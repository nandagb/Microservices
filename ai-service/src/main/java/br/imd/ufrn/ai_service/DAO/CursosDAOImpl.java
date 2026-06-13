package br.imd.ufrn.ai_service.DAO;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CursosDAOImpl implements CursosDAO {
    // @Autowired VectorStore vectorStore;
    private final VectorStore vectorStore;


    public CursosDAOImpl(VectorStore vectorStore) {
        this.vectorStore = vectorStore;

        System.out.println(
            "VectorStore: " +
            vectorStore.getClass().getName()
        );
    }

    @Override
    public void add(List<String> cursos) {
        List<Document> documents = cursos.stream()
            .map(Document::new)
            .toList();
        vectorStore.add(documents);
    }

    @Override
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

    @Override
    public String findClosestMatch(String query) {
        return findClosestMatches(query, 1).get(0);            
    }
}
