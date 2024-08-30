package br.com.gabrielferreira.usuarios.config;

import br.com.gabrielferreira.usuarios.adapters.out.adapters.*;
import br.com.gabrielferreira.usuarios.application.core.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public FindGeneroUseCase findGeneroUseCase(FindDominioAdapter findDominioAdapter){
        return new FindGeneroUseCase(findDominioAdapter);
    }

    @Bean
    public FindTipoTelefoneUseCase findTipoTelefoneUseCase(FindDominioAdapter findDominioAdapter){
        return new FindTipoTelefoneUseCase(findDominioAdapter);
    }

    @Bean
    public FindUsuarioUseCase findUsuarioUseCase(FindUsuarioAdapter findUsuarioAdapter){
        return new FindUsuarioUseCase(findUsuarioAdapter);
    }

    @Bean
    public ValidCreateTelefoneUseCase validCreateTelefoneUseCase(){
        return new ValidCreateTelefoneUseCase();
    }

    @Bean
    public ValidCreateUsuarioUseCase validCreateUsuarioUseCase(FindUsuarioAdapter findUsuarioAdapter){
        return new ValidCreateUsuarioUseCase(findUsuarioAdapter);
    }

    @Bean
    public CreateUsuarioUseCase createUsuarioUseCase(CreateUsuarioAdapter createUsuarioAdapter,
                                                     FindUsuarioAdapter findUsuarioAdapter,
                                                     FindDominioAdapter findGeneroAdapter,
                                                     FindDominioAdapter findTipoTelefoneAdapter){
        return new CreateUsuarioUseCase(createUsuarioAdapter, validCreateUsuarioUseCase(findUsuarioAdapter), validCreateTelefoneUseCase(),
                findGeneroUseCase(findGeneroAdapter), findTipoTelefoneUseCase(findTipoTelefoneAdapter));
    }

    @Bean
    public FindTelefoneUseCase findTelefoneUseCase(FindTelefoneAdapter findTelefoneAdapter){
        return new FindTelefoneUseCase(findTelefoneAdapter);
    }

    @Bean
    public UpdateTelefoneUseCase updateTelefoneUseCase(UpdateTelefoneAdapter updateTelefoneAdapter,
                                                       FindDominioAdapter findTipoTelefoneAdapter,
                                                       FindTelefoneAdapter findTelefoneAdapter,
                                                       TelefoneMapperAdapter telefoneMapperAdapter){
        return new UpdateTelefoneUseCase(updateTelefoneAdapter, validCreateTelefoneUseCase(), findTipoTelefoneUseCase(findTipoTelefoneAdapter), findTelefoneUseCase(findTelefoneAdapter), telefoneMapperAdapter);
    }

    @Bean
    public UpdateUsuarioUseCase updateUsuarioUseCase(UpdateUsuarioAdapter updateUsuarioAdapter,
                                                     FindUsuarioAdapter findUsuarioAdapter,
                                                     FindDominioAdapter findDominioAdapter,
                                                     UsuarioMapperAdapter usuarioMapperAdapter){
        return new UpdateUsuarioUseCase(updateUsuarioAdapter, findUsuarioUseCase(findUsuarioAdapter), validCreateUsuarioUseCase(findUsuarioAdapter), findGeneroUseCase(findDominioAdapter), usuarioMapperAdapter);
    }

    @Bean
    public DeleteUsuarioUseCase deleteUsuarioUseCase(DeleteUsuarioAdapter deleteUsuarioAdapter,
                                                     FindUsuarioAdapter findUsuarioAdapter){
        return new DeleteUsuarioUseCase(deleteUsuarioAdapter, findUsuarioUseCase(findUsuarioAdapter));
    }

    @Bean
    public ValidCreateAnotacaoUseCase validCreateAnotacaoUseCase(){
        return new ValidCreateAnotacaoUseCase();
    }

    @Bean
    public FindTipoAnotacaoUseCase findTipoAnotacaoUseCase(FindDominioAdapter findDominioAdapter){
        return new FindTipoAnotacaoUseCase(findDominioAdapter);
    }

    @Bean
    public FindSituacaoAnotacaoUseCase findSituacaoAnotacaoUseCase(FindDominioAdapter findDominioAdapter){
        return new FindSituacaoAnotacaoUseCase(findDominioAdapter);
    }

    @Bean
    public CreateAnotacaoRascunhoUseCase createAnotacaoRascunhoUseCase(CreateAnotacaoRascunhoRascunhoAdapter createAnotacaoRascunhoAdapter,
                                                                       FindDominioAdapter findTipoAnotacao,
                                                                       FindUsuarioAdapter findUsuarioAdapter,
                                                                       FindDominioAdapter findSituacaoAnotacao){
        return new CreateAnotacaoRascunhoUseCase(createAnotacaoRascunhoAdapter, findTipoAnotacaoUseCase(findTipoAnotacao), findUsuarioUseCase(findUsuarioAdapter),
                findSituacaoAnotacaoUseCase(findSituacaoAnotacao), validCreateAnotacaoUseCase());
    }

    @Bean
    public FindAnotacaoRascunhoUseCase findAnotacaoUseCase(FindAnotacaoRascunhoAdapter findAnotacaoRascunhoAdapter){
        return new FindAnotacaoRascunhoUseCase(findAnotacaoRascunhoAdapter);
    }
}
