package br.imd.ufrn.userservice.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.imd.ufrn.userservice.Exception.UserNotFoundException;
import br.imd.ufrn.userservice.Model.User;
import br.imd.ufrn.userservice.Repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/test")
    public String test(@RequestParam String name) {
        return "Olá " + name;
    }

    @GetMapping("/all")
    public List<User> all() {
        return repository.findAll();
    }

    @PostMapping("/add")
    User addUser(@RequestBody User user) {
        return repository.save(user);
    }

    @GetMapping("/{id}")
    User getUser(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/edit/{id}")
    User editUser(@RequestBody User newUser, @PathVariable Long id) {
        
        return repository.findById(id)
        .map(user -> {
            user.setName(newUser.getName());
            user.setAge(newUser.getAge());
            user.setCity(newUser.getCity());
            user.setMotherName(newUser.getMotherName());
            user.setFatherName(newUser.getFatherName());
            return repository.save(user);
        })
        .orElseGet(() -> {
            return repository.save(newUser);
        });
    }

    @DeleteMapping("/delete/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
