package br.imd.ufrn.ai_service.Context;

import java.util.List;

public class UserData {
     public static final List<String> USERS = List.of(
        """
        Usuário: Fernanda
        Nome Completo: Fernanda Gonçalves Barros
        Idade: 26
        Cidade: Brasília
        Nome do Pai: Fernando César Avelino Barros
        Nome da Mãe: Ivana Gonçalves Soares
        """,

        """
        Usuário: Ivana
        Nome Completo: Ivana Gonçalves Soares
        Idade: 64
        Cidade: Campina Grande
        Nome do Pai: Jason Gonçalves de Lima
        Nome da Mãe: Materna Gonçalves Soares
        """,

        """
        Usuário: Fernando
        Nome Completo: Fernando César Avelino Barros
        Idade: 65
        Cidade: Recife
        Nome do Pai: Geraldo Barros
        Nome da Mãe: Ruth Barros
        """,

        """
        Usuário: Ezequiel
        Nome Completo: Ezequiel Morais da Rocha e Silva
        Idade: 24
        Cidade: Natal
        Nome do Pai: Sairo 
        Nome da Mãe: Silvia
        """
    );
}
