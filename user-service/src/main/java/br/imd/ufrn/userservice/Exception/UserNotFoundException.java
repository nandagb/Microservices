package br.imd.ufrn.userservice.Exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Não foi possível encontrar o usuário " + id);
    }
}
