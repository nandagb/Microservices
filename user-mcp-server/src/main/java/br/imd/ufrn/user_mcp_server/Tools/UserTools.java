package br.imd.ufrn.user_mcp_server.Tools;

import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import br.imd.ufrn.user_mcp_server.Model.User;
import br.imd.ufrn.user_mcp_server.Service.UserService;

@Component
public class UserTools {
    private final UserService service;

    public UserTools(UserService service) {
        this.service = service;
    }

    @Tool(description = "Lista todos os usuários")
    public List<User> allUsers() {
        return service.all();
    }

    @Tool(description = "Conta quantos usuários estão cadastrados")
    public int allUsersSize() {
        List<User> users = service.all();
        return users.size();
    }

    @Tool(description = "Busca usuário pelo ID")
    public User getUserById(Long id) {
        return service.getUserById(id);
    }

    @Tool(description = "Busca usuários pelo nome")
    public List<User> getUsersByName(String name) {
        return service.getUsersByName(name);
    }
}
