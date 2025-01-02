package br.com.gabrielferreira.usuarios.utils.exemplo.swagger;

public class ExemploAnotacaoLembreteUtils {

    private ExemploAnotacaoLembreteUtils() {}

    public static final String ANOTACAO_LEMBRETE_CRIADO =
            """
                    {
                      "id": 1,
                      "titulo": "Anotação lembrete #1",
                      "descricao": "lembrete",
                      "tipoAnotacao": {
                        "id": 8,
                        "descricao": "Lembrete",
                        "codigo": "LEMBRETE",
                        "tipo": {
                          "id": 3,
                          "descricao": "Tipo de anotação",
                          "codigo": "TIPO_ANOTACAO"
                        }
                      },
                      "situacaoTipoAnotacao": {
                        "id": 13,
                        "descricao": "Lembrete em aberto",
                        "codigo": "LEMBRETE_ABERTO",
                        "tipo": {
                          "id": 4,
                          "descricao": "Situação do tipo de anotação",
                          "codigo": "SITUACAO_TIPO_ANOTACAO"
                        }
                      },
                      "dataLembrete": "2025-01-02T15:30:00Z",
                      "createdAt": "2025-01-01T19:26:05.0958744Z",
                      "updatedAt": null,
                      "_links": {
                        "self": [
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/lembretes/24",
                            "type": "GET"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/lembretes/24",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/lembretes/24/reabrir",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/lembretes/24/finalizar",
                            "type": "PUT"
                          }
                        ]
                      }
                    }
            """;

    public static final String ANOTACAO_LEMBRETE_ENCONTRADO =
            """
                    {
                      "id": 1,
                      "titulo": "Anotação lembrete #1",
                      "descricao": "lembrete",
                      "tipoAnotacao": {
                        "id": 8,
                        "descricao": "Lembrete",
                        "codigo": "LEMBRETE",
                        "tipo": {
                          "id": 3,
                          "descricao": "Tipo de anotação",
                          "codigo": "TIPO_ANOTACAO"
                        }
                      },
                      "situacaoTipoAnotacao": {
                        "id": 13,
                        "descricao": "Lembrete em aberto",
                        "codigo": "LEMBRETE_ABERTO",
                        "tipo": {
                          "id": 4,
                          "descricao": "Situação do tipo de anotação",
                          "codigo": "SITUACAO_TIPO_ANOTACAO"
                        }
                      },
                      "dataLembrete": "2025-01-02T15:30:00Z",
                      "createdAt": "2025-01-01T19:26:05.095874Z",
                      "updatedAt": null,
                      "_links": {
                        "self": [
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/lembretes/24",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/lembretes/24/reabrir",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/lembretes/24/finalizar",
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

    public static final String ANOTACAO_EDITAR =
            """
                    {
                      "id": 1,
                      "titulo": "Anotação lemnbrete #1",
                      "descricao": "lembrete",
                      "tipoAnotacao": {
                        "id": 8,
                        "descricao": "Lembrete",
                        "codigo": "LEMBRETE",
                        "tipo": {
                          "id": 3,
                          "descricao": "Tipo de anotação",
                          "codigo": "TIPO_ANOTACAO"
                        }
                      },
                      "situacaoTipoAnotacao": {
                        "id": 13,
                        "descricao": "Lembrete em aberto",
                        "codigo": "LEMBRETE_ABERTO",
                        "tipo": {
                          "id": 4,
                          "descricao": "Situação do tipo de anotação",
                          "codigo": "SITUACAO_TIPO_ANOTACAO"
                        }
                      },
                      "dataLembrete": "2025-01-10T15:30:00Z",
                      "createdAt": "2025-01-01T19:26:05.095874Z",
                      "updatedAt": "2025-01-01T19:32:45.873797Z",
                      "_links": {
                        "self": [
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/lembretes/24",
                            "type": "GET"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/lembretes/24/reabrir",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/2/anotacoes/lembretes/24/finalizar",
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
