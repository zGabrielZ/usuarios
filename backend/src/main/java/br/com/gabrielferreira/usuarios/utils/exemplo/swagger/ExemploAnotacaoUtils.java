package br.com.gabrielferreira.usuarios.utils.exemplo.swagger;

public class ExemploAnotacaoUtils {

    private ExemploAnotacaoUtils() {}

    public static final String ANOTACOES_ENCONTRADOS =
            """
                    {
                        "_embedded": {
                            "anotacoes": [
                                {
                                    "id": 5,
                                    "titulo": "Anotação #12345678912340",
                                    "descricao": "Anotação tal..... editado123",
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
                                    "createdAt": "2024-08-29T21:42:15.692502-03:00",
                                    "updatedAt": "2024-09-02T22:34:54.029916-03:00",
                                    "_links": {
                                        "self": {
                                            "href": "/api/v1/usuarios/2/anotacoes/rascunhos/5",
                                            "type": "GET"
                                        }
                                    }
                                }
                            ]
                        },
                        "_links": {
                            "self": {
                                "href": "/api/v1/usuarios/2/anotacoes{?titulo,descricao}",
                                "type": "GET",
                                "templated": true
                            }
                        },
                        "page": {
                            "size": 5,
                            "totalElements": 5,
                            "totalPages": 1,
                            "number": 0
                        }
                    }
            """;
}
