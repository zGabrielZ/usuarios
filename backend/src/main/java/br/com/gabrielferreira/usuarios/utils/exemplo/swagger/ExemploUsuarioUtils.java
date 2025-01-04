package br.com.gabrielferreira.usuarios.utils.exemplo.swagger;

public class ExemploUsuarioUtils {

    private ExemploUsuarioUtils() {}

    public static final String USUARI0_CRIAR_ERRO =
            """
                    {
                      "dataAtual": "2024-12-18T21:05:16.6614872Z",
                      "status": 400,
                      "titulo": "Regra de negócio",
                      "mensagem": "Não vai ser possível cadastrar este usuário pois o e-mail 'teste@email.com' já foi cadastrado",
                      "caminhoUrl": "/api/v1/usuarios",
                      "campos": null
                    }
            """;

    public static final String USUARI0_CRIAR_NAO_ENCONTRADO =
            """
                    {
                      "dataAtual": "2024-12-18T21:05:16.6614872Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Tipo de telefone informado não encontrado",
                      "caminhoUrl": "/api/v1/usuarios",
                      "campos": null
                    }
            """;

    public static final String USUARI0_NAO_ENCONTRADO =
            """
                    {
                      "dataAtual": "2024-12-18T21:05:16.6614872Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Usuário informado não encontrado",
                      "caminhoUrl": "/api/v1/usuarios/10",
                      "campos": null
                    }
            """;

    public static final String USUARI0_ATUALIZAR_NAO_ENCONTRADO =
            """
                    {
                      "dataAtual": "2024-12-18T21:05:16.6614872Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Usuário informado não encontrado",
                      "caminhoUrl": "/api/v1/usuarios/20",
                      "campos": null
                    }
            """;

    public static final String USUARI0_DELETAR_NAO_ENCONTRADO =
            """
                    {
                      "dataAtual": "2024-12-18T21:05:16.6614872Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Usuário informado não encontrado",
                      "caminhoUrl": "/api/v1/usuarios/20",
                      "campos": null
                    }
            """;

    public static final String USUARI0_ATUALIZAR_ADMIN_NAO_ENCONTRADO =
            """
                    {
                      "dataAtual": "2024-12-18T21:05:16.6614872Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Usuário informado não encontrado",
                      "caminhoUrl": "/api/v1/usuarios/20/admin",
                      "campos": null
                    }
            """;

    public static final String USUARI0_ATUALIZAR_CLIENT_NAO_ENCONTRADO =
            """
                    {
                      "dataAtual": "2024-12-18T21:05:16.6614872Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Usuário informado não encontrado",
                      "caminhoUrl": "/api/v1/usuarios/20/client",
                      "campos": null
                    }
            """;

    public static final String PERFIL_NAO_ENCONTRADO =
            """
                    {
                      "dataAtual": "2024-12-18T21:05:16.6614872Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Perfil informado não encontrado",
                      "caminhoUrl": "/api/v1/usuarios/10/perfis/10",
                      "campos": null
                    }
            """;
}
