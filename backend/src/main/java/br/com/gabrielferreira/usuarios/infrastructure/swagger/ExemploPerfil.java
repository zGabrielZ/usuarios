package br.com.gabrielferreira.usuarios.infrastructure.swagger;

public class ExemploPerfil {

    private ExemploPerfil() {}

    public static final String PERFIL_NAO_ENCONTRADO =
            """
                    {
                      "dataAtual": "2024-12-18T21:05:16.6614872Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Perfil informado não encontrado",
                      "caminhoUrl": "/api/v1/perfis/3",
                      "campos": null
                    }
            """;
}
