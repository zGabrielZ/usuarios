package br.com.gabrielferreira.usuarios.config;

import br.com.gabrielferreira.usuarios.adapters.out.adapters.CreateUsuarioAdapter;
import br.com.gabrielferreira.usuarios.adapters.out.adapters.FindDominioAdapter;
import br.com.gabrielferreira.usuarios.adapters.out.adapters.FindUsuarioAdapter;
import br.com.gabrielferreira.usuarios.application.core.usecase.CreateUsuarioUseCase;
import br.com.gabrielferreira.usuarios.application.core.usecase.ValidCreateTelefoneUseCase;
import br.com.gabrielferreira.usuarios.application.core.usecase.ValidCreateUsuarioUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreateUsuarioUseCase createUsuarioUseCase(CreateUsuarioAdapter createUsuarioAdapter,
                                                     ValidCreateUsuarioUseCase validCreateUsuarioUseCase,
                                                     ValidCreateTelefoneUseCase validCreateTelefoneUseCase){
        return new CreateUsuarioUseCase(createUsuarioAdapter, validCreateUsuarioUseCase, validCreateTelefoneUseCase);
    }

    @Bean
    public ValidCreateUsuarioUseCase validCreateUsuarioUseCase(FindUsuarioAdapter findUsuarioAdapter,
                                                               FindDominioAdapter findDominioAdapter){
        return new ValidCreateUsuarioUseCase(findUsuarioAdapter, findDominioAdapter);
    }

    @Bean
    public ValidCreateTelefoneUseCase validCreateTelefoneUseCase(FindDominioAdapter findDominioAdapter){
        return new ValidCreateTelefoneUseCase(findDominioAdapter);
    }
}
