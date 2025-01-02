package br.com.gabrielferreira.usuarios.utils.exemplo.swagger;

public class ExemploTelefoneUtils {

    private ExemploTelefoneUtils() {}

    public static final String TELEFONE_ENCONTRADO =
            """
                    {
                      "id": 1,
                      "numero": "999999999",
                      "ddd": "11",
                      "telefoneFormatado": "(11) 99999-9999",
                      "descricao": "Celular",
                      "tipoTelefone": {
                        "id": 5,
                        "descricao": "Celular",
                        "codigo": "CELULAR",
                        "tipo": {
                          "id": 2,
                          "descricao": "Tipo de telefone",
                          "codigo": "TIPO_TELEFONE"
                        },
                        "_links": {
                          "self": {
                            "href": "/api/v1/tipos-telefones/5",
                            "type": "GET"
                          }
                        }
                      },
                      "createdAt": "2024-08-15T22:24:39.949223Z",
                      "updatedAt": "2024-12-19T20:19:40.889613Z",
                      "_links": {
                        "self": {
                          "href": "/api/v1/usuarios/2/telefones/2",
                          "type": "PUT"
                        }
                      }
                    }
            """;

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

    public static final String TELEFONE_ATUALIZADO =
            """
                    {
                      "id": 1,
                      "numero": "999999999",
                      "ddd": "11",
                      "telefoneFormatado": "(11) 99999-9999",
                      "descricao": "Celular",
                      "tipoTelefone": {
                        "id": 5,
                        "descricao": "Celular",
                        "codigo": "CELULAR",
                        "tipo": {
                          "id": 2,
                          "descricao": "Tipo de telefone",
                          "codigo": "TIPO_TELEFONE"
                        },
                        "_links": {
                          "self": {
                            "href": "/api/v1/tipos-telefones/5",
                            "type": "GET"
                          }
                        }
                      },
                      "createdAt": "2024-08-15T22:24:39.949223Z",
                      "updatedAt": "2024-12-19T20:19:40.889613Z",
                      "_links": {
                        "self": {
                          "href": "/api/v1/usuarios/2/telefones",
                          "type": "GET"
                        }
                      }
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
