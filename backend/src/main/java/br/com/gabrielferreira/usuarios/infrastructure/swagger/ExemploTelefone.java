package br.com.gabrielferreira.usuarios.infrastructure.swagger;

public class ExemploTelefone {

    private ExemploTelefone() {}

    public static final String TELEFONE_NAO_ENCONTRADO =
            """
                    {
                      "dataAtual": "2024-12-18T21:05:16.6614872Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Telefone informado não encontrado",
                      "caminhoUrl": "/api/v1/usuarios/20/telefones",
                      "campos": null
                    }
            """;

    public static final String TELEFONE_ATUALIZAR_NAO_ENCONTRADO =
            """
                    {
                      "dataAtual": "2024-12-18T21:05:16.6614872Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Telefone informado não encontrado",
                      "caminhoUrl": "/api/v1/usuarios/20/telefones/20",
                      "campos": null
                    }
            """;

    public static final String TELEFONE_ATUALIZAR_ERRO =
            """
                    {
                      "dataAtual": "2024-12-18T21:05:16.6614872Z",
                      "status": 400,
                      "titulo": "Regra de negócio",
                      "mensagem": "O número do telefone '(11) 9999-9999' tem ser do tipo residencial",
                      "caminhoUrl": "/api/v1/usuarios/20/telefones/20",
                      "campos": null
                    }
            """;
}
