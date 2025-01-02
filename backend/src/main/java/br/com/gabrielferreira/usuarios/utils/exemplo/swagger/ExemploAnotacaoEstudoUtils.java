package br.com.gabrielferreira.usuarios.utils.exemplo.swagger;

public class ExemploAnotacaoEstudoUtils {

    private ExemploAnotacaoEstudoUtils() {}

    public static final String ANOTACAO_ESTUDO_CRIADO =
            """
                    {
                      "id": 1,
                      "titulo": "Anotação estudo #1",
                      "descricao": "Estudo #1",
                      "tipoAnotacao": {
                        "id": 7,
                        "descricao": "Estudo",
                        "codigo": "ESTUDO",
                        "tipo": {
                          "id": 3,
                          "descricao": "Tipo de anotação",
                          "codigo": "TIPO_ANOTACAO"
                        }
                      },
                      "situacaoTipoAnotacao": {
                        "id": 9,
                        "descricao": "Estudo em andamento",
                        "codigo": "ESTUDO_ANDAMENTO",
                        "tipo": {
                          "id": 4,
                          "descricao": "Situação do tipo de anotação",
                          "codigo": "SITUACAO_TIPO_ANOTACAO"
                        }
                      },
                      "dataEstudoInicio": "2024-12-31T20:00:00Z",
                      "dataEstudoFim": "2024-12-31T22:00:00Z",
                      "createdAt": "2024-12-29T20:50:44.0632193Z",
                      "updatedAt": null,
                      "_links": {
                        "self": [
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/estudos/23",
                            "type": "GET"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/estudos/23",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/estudos/23/reabrir",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/estudos/23/finalizar",
                            "type": "PUT"
                          }
                        ]
                      }
                    }
            """;

    public static final String ANOTACAO_ESTUDO_ENCONTRADO =
            """
                    {
                      "id": 1,
                      "titulo": "Anotação estudo #1",
                      "descricao": "Anotação estudo #1",
                      "tipoAnotacao": {
                        "id": 7,
                        "descricao": "Estudo",
                        "codigo": "ESTUDO",
                        "tipo": {
                          "id": 3,
                          "descricao": "Tipo de anotação",
                          "codigo": "TIPO_ANOTACAO"
                        }
                      },
                      "situacaoTipoAnotacao": {
                        "id": 9,
                        "descricao": "Estudo em andamento",
                        "codigo": "ESTUDO_ANDAMENTO",
                        "tipo": {
                          "id": 4,
                          "descricao": "Situação do tipo de anotação",
                          "codigo": "SITUACAO_TIPO_ANOTACAO"
                        }
                      },
                      "dataEstudoInicio": "2024-12-31T20:00:00Z",
                      "dataEstudoFim": "2024-12-31T22:00:00Z",
                      "createdAt": "2024-12-29T20:50:44.063219Z",
                      "updatedAt": null,
                      "_links": {
                        "self": [
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/estudos/23",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/estudos/23/reabrir",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/estudos/23/finalizar",
                            "type": "PUT"
                          }
                        ]
                      }
                    }
            """;

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

    public static final String ANOTACAO_EDITAR =
            """
                    {
                      "id": 1,
                      "titulo": "Anotação estudo #1",
                      "descricao": "Anotaçao estudo #1",
                      "tipoAnotacao": {
                        "id": 7,
                        "descricao": "Estudo",
                        "codigo": "ESTUDO",
                        "tipo": {
                          "id": 3,
                          "descricao": "Tipo de anotação",
                          "codigo": "TIPO_ANOTACAO"
                        }
                      },
                      "situacaoTipoAnotacao": {
                        "id": 9,
                        "descricao": "Estudo em andamento",
                        "codigo": "ESTUDO_ANDAMENTO",
                        "tipo": {
                          "id": 4,
                          "descricao": "Situação do tipo de anotação",
                          "codigo": "SITUACAO_TIPO_ANOTACAO"
                        }
                      },
                      "dataEstudoInicio": "2024-12-31T22:00:00Z",
                      "dataEstudoFim": "2025-01-01T00:00:00Z",
                      "createdAt": "2024-12-29T20:50:44.063219Z",
                      "updatedAt": "2024-12-29T21:02:36.088269Z",
                      "_links": {
                        "self": [
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/estudos/23",
                            "type": "GET"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/estudos/23/reabrir",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/estudos/23/finalizar",
                            "type": "PUT"
                          }
                        ]
                      }
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
