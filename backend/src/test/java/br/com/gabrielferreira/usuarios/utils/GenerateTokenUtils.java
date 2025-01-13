package br.com.gabrielferreira.usuarios.utils;

import br.com.gabrielferreira.usuarios.adapters.in.controller.request.LoginDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static br.com.gabrielferreira.usuarios.tests.LoginFactory.criarLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Component
public class GenerateTokenUtils {

    @Autowired
    protected ObjectMapper objectMapper;

    public String gerarToken(MockMvc mockMvc, String email, String senha) {
        LoginDTO loginDto = criarLogin(email, senha);
        try {
            String jsonBody = objectMapper.writeValueAsString(loginDto);

            ResultActions resultActions = mockMvc
                    .perform(post("/login")
                            .content(jsonBody)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON));
            String resultado = resultActions.andReturn().getResponse().getContentAsString();

            JacksonJsonParser jsonParser = new JacksonJsonParser();
            return jsonParser.parseMap(resultado).get("token").toString();
        } catch (Exception e){
            throw new RuntimeException("Erro ao logar com o usuário " + email);
        }
    }
}
