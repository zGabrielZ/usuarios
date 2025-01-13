package br.com.gabrielferreira.usuarios.tests;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.LoginDTO;

public class LoginFactory {

    private LoginFactory() {}

    public static LoginDTO criarLogin(String email, String senha) {
        return new LoginDTO(email, senha);
    }
}
