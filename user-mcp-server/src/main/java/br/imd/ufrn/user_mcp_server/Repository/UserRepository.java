package br.imd.ufrn.user_mcp_server.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.imd.ufrn.user_mcp_server.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
}
