package br.com.gabrielferreira.usuarios.utils.exemplo.swagger;

public class ExemploAnotacaoEstudoUtils {

    private ExemploAnotacaoEstudoUtils() {}

    public static final String ANOTACAO_NAO_ENCONTRADA =
            """
                    {
                      "dataAtual": "2024-12-29T20:57:17.8298735Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Anotação informado não encontrado",
                      "caminhoUrl": "/api/v1/usuarios/2/anotacoes/estudos/24",
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
                      "caminhoUrl": "/v1/usuarios/2/anotacoes/estudos/24/finalizar",
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
                      "caminhoUrl": "/v1/usuarios/2/anotacoes/estudos/24/finalizar",
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
                      "caminhoUrl": "/v1/usuarios/2/anotacoes/estudos/24/reabrir",
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
                      "caminhoUrl": "/v1/usuarios/2/anotacoes/estudos/24/reabrir",
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
                      "caminhoUrl": "/v1/usuarios/2/anotacoes/estudos/24",
                      "campos": null
                    }
            """;

    public static final String ANOTACAO_EDITAR_ERRO =
            """
                    {
                      "dataAtual": "2024-12-29T20:57:17.8298735Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "A data início do estudo não pode ser antes ou igual ao data fim do estudo",
                      "caminhoUrl": "/v1/usuarios/2/anotacoes/estudos/24",
                      "campos": null
                    }
            """;
}
