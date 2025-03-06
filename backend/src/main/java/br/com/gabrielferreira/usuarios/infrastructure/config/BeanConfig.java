package br.com.gabrielferreira.usuarios.infrastructure.config;

import br.com.gabrielferreira.usuarios.adapters.out.adapters.*;
import br.com.gabrielferreira.usuarios.application.core.usecase.*;
import br.com.gabrielferreira.usuarios.application.validator.AnotacaoValidator;
import br.com.gabrielferreira.usuarios.application.validator.TelefoneValidator;
import br.com.gabrielferreira.usuarios.application.validator.UsuarioValidator;
import br.com.gabrielferreira.usuarios.application.validator.impl.AnotacaoValidatorImpl;
import br.com.gabrielferreira.usuarios.application.validator.impl.TelefoneValidatorImpl;
import br.com.gabrielferreira.usuarios.application.validator.impl.UsuarioValidatorImpl;
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
    public FindUsuarioUseCase findUsuarioUseCase(FindUsuarioAdapter findUsuarioAdapter,
                                                 UserCurrentAdapter userCurrentAdapter){
        return new FindUsuarioUseCase(findUsuarioAdapter, userCurrentUseCase(userCurrentAdapter));
    }

    @Bean
    public TelefoneValidator telefoneValidator(){
        return new TelefoneValidatorImpl();
    }

    @Bean
    public UsuarioValidator usuarioValidator(FindUsuarioAdapter findUsuarioAdapter){
        return new UsuarioValidatorImpl(findUsuarioAdapter);
    }

    @Bean
    public CreateUsuarioUseCase createUsuarioUseCase(CreateUsuarioAdapter createUsuarioAdapter,
                                                     FindUsuarioAdapter findUsuarioAdapter,
                                                     FindDominioAdapter findGeneroAdapter,
                                                     FindDominioAdapter findTipoTelefoneAdapter,
                                                     FindPerfilAdapter findPerfilAdapter,
                                                     PasswordEncoderAdapter passwordEncoderAdapter,
                                                     UserCurrentAdapter userCurrentAdapter){
        return new CreateUsuarioUseCase(createUsuarioAdapter, usuarioValidator(findUsuarioAdapter), telefoneValidator(),
                findGeneroUseCase(findGeneroAdapter), findTipoTelefoneUseCase(findTipoTelefoneAdapter), findPerfilUseCase(findPerfilAdapter, userCurrentAdapter),
                passwordEncoderAdapter);
    }

    @Bean
    public FindTelefoneUseCase findTelefoneUseCase(FindTelefoneAdapter findTelefoneAdapter,
                                                   UserCurrentAdapter userCurrentAdapter){
        return new FindTelefoneUseCase(findTelefoneAdapter, userCurrentUseCase(userCurrentAdapter));
    }

    @Bean
    public UpdateTelefoneUseCase updateTelefoneUseCase(UpdateTelefoneAdapter updateTelefoneAdapter,
                                                       FindDominioAdapter findTipoTelefoneAdapter,
                                                       FindTelefoneAdapter findTelefoneAdapter,
                                                       UserCurrentAdapter userCurrentAdapter){
        return new UpdateTelefoneUseCase(updateTelefoneAdapter, telefoneValidator(), findTipoTelefoneUseCase(findTipoTelefoneAdapter), findTelefoneUseCase(findTelefoneAdapter, userCurrentAdapter),
                userCurrentUseCase(userCurrentAdapter));
    }

    @Bean
    public UpdateUsuarioUseCase updateUsuarioUseCase(UpdateUsuarioAdapter updateUsuarioAdapter,
                                                     FindUsuarioAdapter findUsuarioAdapter,
                                                     FindDominioAdapter findDominioAdapter,
                                                     FindPerfilAdapter findPerfilAdapter,
                                                     UserCurrentAdapter userCurrentAdapter){
        return new UpdateUsuarioUseCase(updateUsuarioAdapter, findUsuarioUseCase(findUsuarioAdapter, userCurrentAdapter), usuarioValidator(findUsuarioAdapter), findGeneroUseCase(findDominioAdapter), findPerfilUseCase(findPerfilAdapter, userCurrentAdapter),
                userCurrentUseCase(userCurrentAdapter));
    }

    @Bean
    public DeleteUsuarioUseCase deleteUsuarioUseCase(DeleteUsuarioAdapter deleteUsuarioAdapter,
                                                     FindUsuarioAdapter findUsuarioAdapter,
                                                     UserCurrentAdapter userCurrentAdapter){
        return new DeleteUsuarioUseCase(deleteUsuarioAdapter, findUsuarioUseCase(findUsuarioAdapter, userCurrentAdapter), userCurrentUseCase(userCurrentAdapter));
    }

    @Bean
    public AnotacaoValidator anotacaoValidator(){
        return new AnotacaoValidatorImpl();
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
                                                       FindDominioAdapter findSituacaoAnotacao,
                                                       UserCurrentAdapter userCurrentAdapter){
        return new CreateAnotacaoUseCase(createAnotacaoAdapter, anotacaoValidator(), findTipoAnotacaoUseCase(findTipoAnotacao),
                findSituacaoAnotacaoUseCase(findSituacaoAnotacao), findUsuarioUseCase(findUsuarioAdapter, userCurrentAdapter), userCurrentUseCase(userCurrentAdapter));
    }

    @Bean
    public FindAnotacaoUseCase findAnotacaoUseCase(FindAnotacaoAdapter findAnotacaoAdapter,
                                                   FindUsuarioAdapter findUsuarioAdapter,
                                                   UserCurrentAdapter userCurrentAdapter){
        return new FindAnotacaoUseCase(findAnotacaoAdapter, findUsuarioUseCase(findUsuarioAdapter, userCurrentAdapter), userCurrentUseCase(userCurrentAdapter));
    }

    @Bean
    public UpdateAnotacaoUseCase updateAnotacaoUseCase(UpdateAnotacaoAdapter updateAnotacaoAdapter,
                                                       FindAnotacaoAdapter findAnotacaoAdapter,
                                                       FindDominioAdapter findDominioAdapter,
                                                       FindUsuarioAdapter findUsuarioAdapter,
                                                       UserCurrentAdapter userCurrentAdapter){
        return new UpdateAnotacaoUseCase(updateAnotacaoAdapter, findAnotacaoUseCase(findAnotacaoAdapter, findUsuarioAdapter, userCurrentAdapter), findSituacaoAnotacaoUseCase(findDominioAdapter), anotacaoValidator(),
                userCurrentUseCase(userCurrentAdapter));
    }

    @Bean
    public FindPerfilUseCase findPerfilUseCase(FindPerfilAdapter findPerfilAdapter,
                                               UserCurrentAdapter userCurrentAdapter){
        return new FindPerfilUseCase(findPerfilAdapter, userCurrentUseCase(userCurrentAdapter));
    }

    @Bean
    public GenerateTokenUseCase generateTokenUseCase(GenerateTokenAdapter generateTokenAdapter) {
        return new GenerateTokenUseCase(generateTokenAdapter);
    }
}
