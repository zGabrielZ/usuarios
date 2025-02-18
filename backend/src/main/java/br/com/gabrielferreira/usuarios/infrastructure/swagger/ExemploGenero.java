package br.com.gabrielferreira.usuarios.infrastructure.swagger;

public class ExemploGenero {

    private ExemploGenero() {}

    public static final String GENERO_NAO_ENCONTRADO =
            """
                    {
                      "dataAtual": "2024-12-18T21:05:16.6614872Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Gênero informado não encontrado",
                      "caminhoUrl": "/api/v1/generos/10",
                      "campos": null
                    }
            """;
}
