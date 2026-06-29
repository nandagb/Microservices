package br.imd.ufrn.user_mcp_server.Exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Não foi possível encontrar o usuário " + id);
    }
}
