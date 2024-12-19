package br.com.gabrielferreira.usuarios.utils.exemplo.swagger;

public class ExemploGeneroUtils {

    private ExemploGeneroUtils() {}

    public static final String GENERO_ENCONTRADO =
            """
                    {
                        "id": 1,
                        "descricao": "Masculino",
                        "codigo": "MASCULINO",
                        "tipo": {
                            "id": 1,
                            "descricao": "Gênero",
                            "codigo": "GENERO"
                        }
                    }
            """;

    public static final String GENERO_NAO_ENCONTRADO =
            """
                    {
                        "dataAtual": "2024-12-18T21:05:16.6614872-03:00",
                        "status": 404,
                        "titulo": "Não encontrado",
                        "mensagem": "Gênero informado não encontrado",
                        "caminhoUrl": "/api/v1/generos/10",
                        "campos": null
                    }
            """;

    public static final String GENEROS_ENCONTRADOS =
            """
                    {
                        "_embedded": {
                            "generos": [
                                {
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
                                }
                            ]
                        },
                        "_links": {
                            "self": {
                                "href": "/api/v1/generos",
                                "type": "GET"
                            }
                        }
                    }
            """;
}
