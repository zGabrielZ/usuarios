package br.com.gabrielferreira.usuarios.utils.exemplo.swagger;

public class ExemploPerfilUtils {

    private ExemploPerfilUtils() {}

    public static final String PERFIL_ENCONTRADO =
            """
                    {
                        "id": 2,
                        "titulo": "Cliente",
                        "autoriedade": "ROLE_CLIENT"
                    }
            """;

    public static final String PERFIL_NAO_ENCONTRADO =
            """
                    {
                        "dataAtual": "2024-12-18T21:05:16.6614872-03:00",
                        "status": 404,
                        "titulo": "Não encontrado",
                        "mensagem": "Perfil informado não encontrado",
                        "caminhoUrl": "/api/v1/perfis/3",
                        "campos": null
                    }
            """;

    public static final String PERFIS_ENCONTRADOS =
            """
                    {
                        "_embedded": {
                            "perfis": [
                                {
                                    "id": 1,
                                    "titulo": "Adminstrador",
                                    "autoriedade": "ROLE_ADMIN",
                                    "_links": {
                                        "self": {
                                            "href": "/api/v1/perfis/1",
                                            "type": "GET"
                                        }
                                    }
                                }
                            ]
                        },
                        "_links": {
                            "self": {
                                "href": "/api/v1/perfis",
                                "type": "GET"
                            }
                        }
                    }
            """;
}
