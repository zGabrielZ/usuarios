package br.com.gabrielferreira.usuarios.infrastructure.config;

import br.com.gabrielferreira.usuarios.adapters.out.adapters.*;
import br.com.gabrielferreira.usuarios.application.core.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public UserCurrentUseCase userCurrentUseCase(UserCurrentAdapter userCurrentAdapter) {
        return new UserCurrentUseCase(userCurrentAdapter);
    }

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
    public CreateUsuarioUseCase createUsuarioUseCase(CreateUsuarioAdapter createUsuarioAdapter,
                                                     FindUsuarioAdapter findUsuarioAdapter,
                                                     FindDominioAdapter findGeneroAdapter,
                                                     FindDominioAdapter findTipoTelefoneAdapter,
                                                     FindPerfilAdapter findPerfilAdapter,
                                                     PasswordEncoderAdapter passwordEncoderAdapter){
        return new CreateUsuarioUseCase(createUsuarioAdapter, findUsuarioAdapter,
                findGeneroUseCase(findGeneroAdapter), findTipoTelefoneUseCase(findTipoTelefoneAdapter), findPerfilUseCase(findPerfilAdapter),
                passwordEncoderAdapter);
    }

    @Bean
    public FindTelefoneUseCase findTelefoneUseCase(FindTelefoneAdapter findTelefoneAdapter){
        return new FindTelefoneUseCase(findTelefoneAdapter);
    }

    @Bean
    public UpdateTelefoneUseCase updateTelefoneUseCase(UpdateTelefoneAdapter updateTelefoneAdapter,
                                                       FindDominioAdapter findTipoTelefoneAdapter,
                                                       FindTelefoneAdapter findTelefoneAdapter){
        return new UpdateTelefoneUseCase(updateTelefoneAdapter, findTipoTelefoneUseCase(findTipoTelefoneAdapter), findTelefoneUseCase(findTelefoneAdapter));
    }

    @Bean
    public UpdateUsuarioUseCase updateUsuarioUseCase(UpdateUsuarioAdapter updateUsuarioAdapter,
                                                     FindUsuarioAdapter findUsuarioAdapter,
                                                     FindDominioAdapter findDominioAdapter,
                                                     FindPerfilAdapter findPerfilAdapter){
        return new UpdateUsuarioUseCase(updateUsuarioAdapter, findUsuarioUseCase(findUsuarioAdapter), findGeneroUseCase(findDominioAdapter), findPerfilUseCase(findPerfilAdapter));
    }

    @Bean
    public DeleteUsuarioUseCase deleteUsuarioUseCase(DeleteUsuarioAdapter deleteUsuarioAdapter,
                                                     FindUsuarioAdapter findUsuarioAdapter){
        return new DeleteUsuarioUseCase(deleteUsuarioAdapter, findUsuarioUseCase(findUsuarioAdapter));
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
    public CreateAnotacaoUseCase createAnotacaoUseCase(CreateAnotacaoAdapter createAnotacaoAdapter,
                                                       FindDominioAdapter findTipoAnotacao,
                                                       FindUsuarioAdapter findUsuarioAdapter,
                                                       FindDominioAdapter findSituacaoAnotacao){
        return new CreateAnotacaoUseCase(createAnotacaoAdapter, findTipoAnotacaoUseCase(findTipoAnotacao),
                findSituacaoAnotacaoUseCase(findSituacaoAnotacao), findUsuarioUseCase(findUsuarioAdapter));
    }

    @Bean
    public FindAnotacaoUseCase findAnotacaoUseCase(FindAnotacaoAdapter findAnotacaoAdapter,
                                                   FindUsuarioAdapter findUsuarioAdapter){
        return new FindAnotacaoUseCase(findAnotacaoAdapter, findUsuarioUseCase(findUsuarioAdapter));
    }

    @Bean
    public UpdateAnotacaoUseCase updateAnotacaoUseCase(UpdateAnotacaoAdapter updateAnotacaoAdapter,
                                                       FindAnotacaoAdapter findAnotacaoAdapter,
                                                       FindDominioAdapter findDominioAdapter,
                                                       FindUsuarioAdapter findUsuarioAdapter){
        return new UpdateAnotacaoUseCase(updateAnotacaoAdapter, findAnotacaoUseCase(findAnotacaoAdapter, findUsuarioAdapter), findSituacaoAnotacaoUseCase(findDominioAdapter));
    }

    @Bean
    public FindPerfilUseCase findPerfilUseCase(FindPerfilAdapter findPerfilAdapter){
        return new FindPerfilUseCase(findPerfilAdapter);
    }

    @Bean
    public GenerateTokenUseCase generateTokenUseCase(GenerateTokenAdapter generateTokenAdapter) {
        return new GenerateTokenUseCase(generateTokenAdapter);
    }
}
