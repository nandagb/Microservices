package br.imd.ufrn.userservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.imd.ufrn.userservice.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
