package br.com.gabrielferreira.usuarios.utils.exemplo.swagger;

public class ExemploUsuarioUtils {

    private ExemploUsuarioUtils() {}

    public static final String USUARIO_CRIADO =
            """
                    {
                      "id": 1,
                      "nome": "Usuário #1",
                      "email": "usuario@email.com",
                      "cpf": "99999999999",
                      "cpfFormatado": "999.999.999-99",
                      "renda": 5400,
                      "rendaFormatada": "R$ 5.400,00",
                      "dataNascimento": "1992-10-23",
                      "quantidadeFilhos": 0,
                      "telefone": {
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
                        "createdAt": "2024-12-23T22:11:29.8238264-03:00",
                        "updatedAt": null,
                        "_links": {
                          "self": {
                            "href": "/api/v1/usuarios/10/telefones",
                            "type": "GET"
                          }
                        }
                      },
                      "genero": {
                        "id": 1,
                        "descricao": "Masculino",
                        "codigo": "MASCULINO",
                        "tipo": {
                          "id": 1,
                          "descricao": "Gênero",
                          "codigo": "GENERO"
                        },
                        "_links": {
                          "self": {
                            "href": "/api/v1/generos/1",
                            "type": "GET"
                          }
                        }
                      },
                      "createdAt": "2024-12-23T22:11:29.8207359-03:00",
                      "updatedAt": null,
                      "_links": {
                        "self": [
                          {
                            "href": "/api/v1/usuarios/10",
                            "type": "GET"
                          },
                          {
                            "href": "/api/v1/usuarios/10",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/10",
                            "type": "DELETE"
                          },
                          {
                            "href": "/api/v1/usuarios/10/client",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/10/admin",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/email/teste345%40email.com",
                            "type": "GET"
                          },
                          {
                            "href": "/api/v1/usuarios/cpf/40248788086",
                            "type": "GET"
                          }
                        ]
                      }
                    }
            """;

    public static final String USUARI0_CRIAR_ERRO =
            """
                    {
                      "dataAtual": "2024-12-18T21:05:16.6614872-03:00",
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
                      "dataAtual": "2024-12-18T21:05:16.6614872-03:00",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Tipo de telefone informado não encontrado",
                      "caminhoUrl": "/api/v1/usuarios",
                      "campos": null
                    }
            """;

    public static final String USUARIO_ENCONTRADO =
            """
                    {
                      "id": 1,
                      "nome": "Usuário #1",
                      "email": "usuario@email.com",
                      "cpf": "99999999999",
                      "cpfFormatado": "999.999.999-99",
                      "renda": 5400,
                      "rendaFormatada": "R$ 5.400,00",
                      "dataNascimento": "1992-10-23",
                      "quantidadeFilhos": 0,
                      "telefone": {
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
                        "createdAt": "2024-12-23T22:11:29.823826-03:00",
                        "updatedAt": null,
                        "_links": {
                          "self": {
                            "href": "/api/v1/usuarios/10/telefones",
                            "type": "GET"
                          }
                        }
                      },
                      "genero": {
                        "id": 1,
                        "descricao": "Masculino",
                        "codigo": "MASCULINO",
                        "tipo": {
                          "id": 1,
                          "descricao": "Gênero",
                          "codigo": "GENERO"
                        },
                        "_links": {
                          "self": {
                            "href": "/api/v1/generos/1",
                            "type": "GET"
                          }
                        }
                      },
                      "createdAt": "2024-12-23T22:11:29.820736-03:00",
                      "updatedAt": null,
                      "_links": {
                        "self": [
                          {
                            "href": "/api/v1/usuarios/10",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/10",
                            "type": "DELETE"
                          },
                          {
                            "href": "/api/v1/usuarios/10/client",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/10/admin",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/email/teste345%40email.com",
                            "type": "GET"
                          },
                          {
                            "href": "/api/v1/usuarios/cpf/40248788086",
                            "type": "GET"
                          }
                        ]
                      }
                    }
            """;

    public static final String USUARI0_NAO_ENCONTRADO =
            """
                    {
                      "dataAtual": "2024-12-18T21:05:16.6614872-03:00",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Usuário informado não encontrado",
                      "caminhoUrl": "/api/v1/usuarios/10",
                      "campos": null
                    }
            """;

    public static final String USUARIO_ENCONTRADO_CPF =
            """
                    {
                      "id": 1,
                      "nome": "Usuário #1",
                      "email": "usuario@email.com",
                      "cpf": "99999999999",
                      "cpfFormatado": "999.999.999-99",
                      "renda": 5400,
                      "rendaFormatada": "R$ 5.400,00",
                      "dataNascimento": "1992-10-23",
                      "quantidadeFilhos": 0,
                      "createdAt": "2024-12-23T22:11:29.820736-03:00",
                      "updatedAt": null,
                      "_links": {
                        "self": [
                          {
                            "href": "/api/v1/usuarios/10",
                            "type": "GET"
                          },
                          {
                            "href": "/api/v1/usuarios/10",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/10",
                            "type": "DELETE"
                          },
                          {
                            "href": "/api/v1/usuarios/10/client",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/10/admin",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/email/teste345%40email.com",
                            "type": "GET"
                          }
                        ]
                      }
                    }
            """;

    public static final String USUARIO_ENCONTRADO_EMAIL =
            """
                    {
                      "id": 1,
                      "nome": "Usuário #1",
                      "email": "usuario@email.com",
                      "cpf": "99999999999",
                      "cpfFormatado": "999.999.999-99",
                      "renda": 5400,
                      "rendaFormatada": "R$ 5.400,00",
                      "dataNascimento": "1992-10-23",
                      "quantidadeFilhos": 0,
                      "createdAt": "2024-12-23T22:11:29.820736-03:00",
                      "updatedAt": null,
                      "_links": {
                        "self": [
                          {
                            "href": "/api/v1/usuarios/10",
                            "type": "GET"
                          },
                          {
                            "href": "/api/v1/usuarios/10",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/10",
                            "type": "DELETE"
                          },
                          {
                            "href": "/api/v1/usuarios/10/client",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/10/admin",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/cpf/40248788086",
                            "type": "GET"
                          }
                        ]
                      }
                    }
            """;

    public static final String USUARIO_ATUALIZADO =
            """
                    {
                      "id": 1,
                      "nome": "Usuário #1",
                      "email": "usuario@email.com",
                      "cpf": "99999999999",
                      "cpfFormatado": "999.999.999-99",
                      "renda": 50000,
                      "rendaFormatada": "R$ 50.000,00",
                      "dataNascimento": "1975-11-05",
                      "quantidadeFilhos": 5,
                      "telefone": {
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
                        "createdAt": "2024-12-23T22:11:29.823826-03:00",
                        "updatedAt": null,
                        "_links": {
                          "self": {
                            "href": "/api/v1/usuarios/10/telefones",
                            "type": "GET"
                          }
                        }
                      },
                      "genero": {
                        "id": 1,
                        "descricao": "Masculino",
                        "codigo": "MASCULINO",
                        "tipo": {
                          "id": 1,
                          "descricao": "Gênero",
                          "codigo": "GENERO"
                        },
                        "_links": {
                          "self": {
                            "href": "/api/v1/generos/1",
                            "type": "GET"
                          }
                        }
                      },
                      "createdAt": "2024-12-23T22:11:29.820736-03:00",
                      "updatedAt": null,
                      "_links": {
                        "self": [
                          {
                            "href": "/api/v1/usuarios/10",
                            "type": "GET"
                          },
                          {
                            "href": "/api/v1/usuarios/10",
                            "type": "DELETE"
                          },
                          {
                            "href": "/api/v1/usuarios/10/client",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/10/admin",
                            "type": "PUT"
                          },
                          {
                            "href": "/api/v1/usuarios/email/teste345%40email.com",
                            "type": "GET"
                          },
                          {
                            "href": "/api/v1/usuarios/cpf/40248788086",
                            "type": "GET"
                          }
                        ]
                      }
                    }
            """;

    public static final String USUARI0_ATUALIZAR_NAO_ENCONTRADO =
            """
                    {
                      "dataAtual": "2024-12-18T21:05:16.6614872-03:00",
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
                      "dataAtual": "2024-12-18T21:05:16.6614872-03:00",
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
                      "dataAtual": "2024-12-18T21:05:16.6614872-03:00",
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
                      "dataAtual": "2024-12-18T21:05:16.6614872-03:00",
                      "status": 404,
                      "titulo": "Não encontrado",
                      "mensagem": "Usuário informado não encontrado",
                      "caminhoUrl": "/api/v1/usuarios/20/client",
                      "campos": null
                    }
            """;

    public static final String USUARIOS_ENCONTRADOS =
            """
                    {
                      "_embedded": {
                        "usuarios": [
                          {
                            "id": 1,
                            "nome": "Usuário #1",
                            "email": "usuario@email.com",
                            "cpf": "99999999999",
                            "cpfFormatado": "999.999.999-99",
                            "renda": 50000,
                            "rendaFormatada": "R$ 50.000,00",
                            "dataNascimento": "1975-11-05",
                            "quantidadeFilhos": 5,
                            "createdAt": "2024-08-14T21:26:54.633831-03:00",
                            "updatedAt": "2024-10-15T22:08:25.557393-03:00",
                            "_links": {
                              "self": [
                                {
                                  "href": "/api/v1/usuarios/1",
                                  "type": "GET"
                                },
                                {
                                  "href": "/api/v1/usuarios/1",
                                  "type": "PUT"
                                },
                                {
                                  "href": "/api/v1/usuarios/1",
                                  "type": "DELETE"
                                },
                                {
                                  "href": "/api/v1/usuarios/1/client",
                                  "type": "PUT"
                                },
                                {
                                  "href": "/api/v1/usuarios/1/admin",
                                  "type": "PUT"
                                },
                                {
                                  "href": "/api/v1/usuarios/email/abel%40email.com",
                                  "type": "GET"
                                },
                                {
                                  "href": "/api/v1/usuarios/cpf/88547174605",
                                  "type": "GET"
                                }
                              ]
                            }
                          }
                        ]
                      },
                      "_links": {
                        "self": {
                          "href": "/api/v1/usuarios{?nome,email,renda}",
                          "type": "GET",
                          "templated": true
                        }
                      },
                      "page": {
                        "size": 2,
                        "totalElements": 2,
                        "totalPages": 1,
                        "number": 0
                      }
                    }
            """;
}
