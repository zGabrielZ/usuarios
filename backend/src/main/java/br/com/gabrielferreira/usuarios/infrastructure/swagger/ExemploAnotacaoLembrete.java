package br.com.gabrielferreira.usuarios.infrastructure.swagger;

public class ExemploAnotacaoLembrete {

    private ExemploAnotacaoLembrete() {}

    public static final String ANOTACAO_NAO_ENCONTRADA =
            """
                    {
                      "dataAtual": "2024-12-29T20:57:17.8298735Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Anotação informado não encontrado",
                      "caminhoUrl": "/api/v1/usuarios/2/anotacoes/lembretes/24",
                      "campos": null
                    }
            """;

    public static final String ANOTACAO_FINALIZAR_NAO_ENCONTRADA =
            """
                    {
                      "dataAtual": "2024-12-29T20:57:17.8298735Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Anotação informado não encontrado",
                      "caminhoUrl": "/v1/usuarios/2/anotacoes/lembretes/24/finalizar",
                      "campos": null
                    }
            """;

    public static final String ANOTACAO_FINALIZAR_ERRO =
            """
                    {
                      "dataAtual": "2024-12-29T20:57:17.8298735Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Não é possível finalizar a anotação pois já está finalizado",
                      "caminhoUrl": "/v1/usuarios/2/anotacoes/lembretes/24/finalizar",
                      "campos": null
                    }
            """;

    public static final String ANOTACAO_REABRIR_NAO_ENCONTRADA =
            """
                    {
                      "dataAtual": "2024-12-29T20:57:17.8298735Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Anotação informado não encontrado",
                      "caminhoUrl": "/v1/usuarios/2/anotacoes/lembretes/24/reabrir",
                      "campos": null
                    }
            """;

    public static final String ANOTACAO_REABRIR_ERRO =
            """
                    {
                      "dataAtual": "2024-12-29T20:57:17.8298735Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Não é possível reabrir a anotação pois já está em aberto",
                      "caminhoUrl": "/v1/usuarios/2/anotacoes/lembretes/24/reabrir",
                      "campos": null
                    }
            """;

    public static final String ANOTACAO_EDITAR_NAO_ENCONTRADA =
            """
                    {
                      "dataAtual": "2024-12-29T20:57:17.8298735Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Anotação informado não encontrado",
                      "caminhoUrl": "/v1/usuarios/2/anotacoes/lembretes/24",
                      "campos": null
                    }
            """;

    public static final String ANOTACAO_EDITAR_ERRO =
            """
                    {
                      "dataAtual": "2024-12-29T20:57:17.8298735Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Não é possível editar a anotação pois já está finalizado",
                      "caminhoUrl": "/v1/usuarios/2/anotacoes/lembretes/24",
                      "campos": null
                    }
            """;
}
