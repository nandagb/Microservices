package br.imd.ufrn.ai_service.Embedding;

import java.util.List;

import org.springframework.stereotype.Service;

import br.imd.ufrn.ai_service.DAO.CursosDAO;
import br.imd.ufrn.ai_service.DAO.UsersDAO;

@Service
public class EmbeddingService {
    private final UsersDAO usersDAO;

    public EmbeddingService(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public void save(List<String> users) {
        usersDAO.add(users);
    }

    public List<String> findClosestMatches(String query) {
        return usersDAO.findClosestMatches(query, 5);
    }

    public String findClosestMatch(String query) {
        return usersDAO.findClosestMatch(query);
    }
}
