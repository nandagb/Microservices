package br.imd.ufrn.ai_service.DAO;

import java.util.List;

public interface CursosDAO {
    void add(List<String> cursos);
    List<String> findClosestMatches(String query,int numberOfMatches);
    String findClosestMatch(String query);
}
