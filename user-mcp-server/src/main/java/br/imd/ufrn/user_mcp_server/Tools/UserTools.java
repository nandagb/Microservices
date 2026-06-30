package br.imd.ufrn.user_mcp_server.Tools;

import java.util.List;

import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

import br.imd.ufrn.user_mcp_server.Model.User;
import br.imd.ufrn.user_mcp_server.Service.UserService;

@Component
public class UserTools {
    private final UserService service;

    public UserTools(UserService service) {
        this.service = service;
    }

    @McpTool(description = """
    Retorna a lista completa de usuários cadastrados no sistema.

    Use esta ferramenta quando você precisar:
    - Ver todos os usuários
    - Obter detalhes de múltiplos usuários
    - Fazer qualquer análise baseada na lista de usuários

    A resposta contém todos os campos do usuário (id, nome, idade, cidade e informações dos pais).
    """)
    public List<User> allUsers() {
        return service.all();
    }

    @McpTool(description = """
    Retorna apenas a quantidade total de usuários cadastrados no sistema.

    Use esta ferramenta quando a pergunta for sobre:
    - Quantidade de usuários
    - Número total de registros
    - Estatísticas simples de volume de usuários

    Não use esta ferramenta se for necessário obter detalhes dos usuários.
    """)
    public int allUsersSize() {
        List<User> users = service.all();
        return users.size();
    }

    @McpTool(description = """
    Busca um usuário pelo ID numérico exato.
    Use apenas quando o ID for conhecido.
    Nunca tente inferir o ID a partir de nomes.
    """)
    public User getUserById(Long id) {
        return service.getUserById(id);
    }

    @McpTool(description = """
    Busca usuários pelo nome completo.
    Use esta ferramenta quando o usuário informar um nome.
    Retorna uma lista de usuários que podem corresponder ao nome.
    """)
    public List<User> getUsersByName(String name) {
        return service.getUsersByName(name);
    }
}
