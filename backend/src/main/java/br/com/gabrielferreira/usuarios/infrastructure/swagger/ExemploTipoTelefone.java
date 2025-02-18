package br.com.gabrielferreira.usuarios.infrastructure.swagger;

public class ExemploTipoTelefone {

    private ExemploTipoTelefone() {}

    public static final String TIPO_TELEFONE_NAO_ENCONTRADO =
            """
                    {
                      "dataAtual": "2024-12-18T21:05:16.6614872Z",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Tipo de telefone informado não encontrado",
                      "caminhoUrl": "/api/v1/tipos-telefones/40",
                      "campos": null
                    }
            """;
}
