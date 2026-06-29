package br.imd.ufrn.function;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationFunction {
    @Bean
    public Function<String, String> notifyUserCreated() {
        return str -> {
            String message = "Usuário " + str + " criado com sucesso!";
            System.out.println(message);
            return message;
        };
    }
}
