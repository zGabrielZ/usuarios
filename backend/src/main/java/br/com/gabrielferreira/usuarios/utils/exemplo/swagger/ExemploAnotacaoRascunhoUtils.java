package br.com.gabrielferreira.usuarios.utils.exemplo.swagger;

public class ExemploAnotacaoRascunhoUtils {

    private ExemploAnotacaoRascunhoUtils() {}

    public static final String ANOTACAO_RASCUNHO_CRIADO =
            """
                    {
                      "id": 1,
                      "titulo": "Anotacao rascunho #1",
                      "descricao": "rascunho",
                      "tipoAnotacao": {
                        "id": 6,
                        "descricao": "Rascunho",
                        "codigo": "RASCUNHO",
                        "tipo": {
                          "id": 3,
                          "descricao": "Tipo de anotação",
                          "codigo": "TIPO_ANOTACAO"
                        }
                      },
                      "situacaoTipoAnotacao": {
                        "id": 11,
                        "descricao": "Rascunho em aberto",
                        "codigo": "RASCUNHO_ABERTO",
                        "tipo": {
                          "id": 4,
                          "descricao": "Situação do tipo de anotação",
                          "codigo": "SITUACAO_TIPO_ANOTACAO"
                        }
                      },
                      "createdAt": "2025-01-01T19:48:11.0135844-03:00",
                      "updatedAt": null,
                      "_links": {
                        "self": [
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/rascunhos/25",
                            "type": "GET"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/rascunhos/25",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/rascunhos/25/reabrir",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/rascunhos/25/finalizar",
                            "type": "PUT"
                          }
                        ]
                      }
                    }
            """;

    public static final String ANOTACAO_RASCUNHO_ENCONTRADO =
            """
                    {
                      "id": 1,
                      "titulo": "Anotacao rascunho #1",
                      "descricao": "rascunho",
                      "tipoAnotacao": {
                        "id": 6,
                        "descricao": "Rascunho",
                        "codigo": "RASCUNHO",
                        "tipo": {
                          "id": 3,
                          "descricao": "Tipo de anotação",
                          "codigo": "TIPO_ANOTACAO"
                        }
                      },
                      "situacaoTipoAnotacao": {
                        "id": 11,
                        "descricao": "Rascunho em aberto",
                        "codigo": "RASCUNHO_ABERTO",
                        "tipo": {
                          "id": 4,
                          "descricao": "Situação do tipo de anotação",
                          "codigo": "SITUACAO_TIPO_ANOTACAO"
                        }
                      },
                      "createdAt": "2025-01-01T19:48:11.013584-03:00",
                      "updatedAt": null,
                      "_links": {
                        "self": [
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/rascunhos/25",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/rascunhos/25/reabrir",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/rascunhos/25/finalizar",
                            "type": "PUT"
                          }
                        ]
                      }
                    }
            """;

    public static final String ANOTACAO_NAO_ENCONTRADA =
            """
                    {
                      "dataAtual": "2024-12-29T20:57:17.8298735-03:00",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Anotação informado não encontrado",
                      "caminhoUrl": "/api/v1/usuarios/2/anotacoes/rascunhos/24",
                      "campos": null
                    }
            """;

    public static final String ANOTACAO_FINALIZAR_NAO_ENCONTRADA =
            """
                    {
                      "dataAtual": "2024-12-29T20:57:17.8298735-03:00",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Anotação informado não encontrado",
                      "caminhoUrl": "/v1/usuarios/2/anotacoes/rascunhos/24/finalizar",
                      "campos": null
                    }
            """;

    public static final String ANOTACAO_FINALIZAR_ERRO =
            """
                    {
                      "dataAtual": "2024-12-29T20:57:17.8298735-03:00",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Não é possível finalizar a anotação pois já está finalizado",
                      "caminhoUrl": "/v1/usuarios/2/anotacoes/rascunhos/24/finalizar",
                      "campos": null
                    }
            """;

    public static final String ANOTACAO_REABRIR_NAO_ENCONTRADA =
            """
                    {
                      "dataAtual": "2024-12-29T20:57:17.8298735-03:00",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Anotação informado não encontrado",
                      "caminhoUrl": "/v1/usuarios/2/anotacoes/rascunhos/24/reabrir",
                      "campos": null
                    }
            """;

    public static final String ANOTACAO_REABRIR_ERRO =
            """
                    {
                      "dataAtual": "2024-12-29T20:57:17.8298735-03:00",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Não é possível reabrir a anotação pois já está em aberto",
                      "caminhoUrl": "/v1/usuarios/2/anotacoes/rascunhos/24/reabrir",
                      "campos": null
                    }
            """;

    public static final String ANOTACAO_EDITAR =
            """
                    {
                      "id": 1,
                      "titulo": "Anotacao rascunho #1",
                      "descricao": "rascunho",
                      "tipoAnotacao": {
                        "id": 6,
                        "descricao": "Rascunho",
                        "codigo": "RASCUNHO",
                        "tipo": {
                          "id": 3,
                          "descricao": "Tipo de anotação",
                          "codigo": "TIPO_ANOTACAO"
                        }
                      },
                      "situacaoTipoAnotacao": {
                        "id": 11,
                        "descricao": "Rascunho em aberto",
                        "codigo": "RASCUNHO_ABERTO",
                        "tipo": {
                          "id": 4,
                          "descricao": "Situação do tipo de anotação",
                          "codigo": "SITUACAO_TIPO_ANOTACAO"
                        }
                      },
                      "createdAt": "2025-01-01T19:48:11.013584-03:00",
                      "updatedAt": "2025-01-01T19:55:28.409241-03:00",
                      "_links": {
                        "self": [
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/rascunhos/25",
                            "type": "GET"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/rascunhos/25/reabrir",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/rascunhos/25/finalizar",
                            "type": "PUT"
                          }
                        ]
                      }
                    }
            """;

    public static final String ANOTACAO_EDITAR_NAO_ENCONTRADA =
            """
                    {
                      "dataAtual": "2024-12-29T20:57:17.8298735-03:00",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Anotação informado não encontrado",
                      "caminhoUrl": "/v1/usuarios/2/anotacoes/rascunhos/24",
                      "campos": null
                    }
            """;

    public static final String ANOTACAO_EDITAR_ERRO =
            """
                    {
                      "dataAtual": "2024-12-29T20:57:17.8298735-03:00",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Não é possível editar a anotação pois já está finalizado",
                      "caminhoUrl": "/v1/usuarios/2/anotacoes/rascunhos/24",
                      "campos": null
                    }
            """;
}
