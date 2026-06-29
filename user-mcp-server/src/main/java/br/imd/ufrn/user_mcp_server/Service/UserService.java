package br.imd.ufrn.user_mcp_server.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.imd.ufrn.user_mcp_server.Exception.UserNotFoundException;
import br.imd.ufrn.user_mcp_server.Model.User;
import br.imd.ufrn.user_mcp_server.Repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;
    
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> all() {
        return repository.findAll();
    }

    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> getUsersByName(String name) {
        return repository.findByName(name);
    }
}
