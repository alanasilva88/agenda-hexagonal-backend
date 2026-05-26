package com.example.agenda_hexagonal.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.agenda_hexagonal.application.usecases.ContatoServiceImpl;
import com.example.agenda_hexagonal.domain.ports.in.ContatoUseCase;
import com.example.agenda_hexagonal.infrastructure.adapters.repositories.ContatoRepositoryImpl;

@Configuration // Avisa ao Spring que esta é uma classe de configuração de Beans
public class BeanConfig {

    @Bean // Diz ao Spring que o objeto retornado por este método deve ser gerenciado por ele
    public ContatoUseCase contatoUseCase(ContatoRepositoryImpl contatoRepositoryPort) {
        // Criamos o nosso serviço Java puro manualmente e passamos o adaptador de banco para ele
        return new ContatoServiceImpl(contatoRepositoryPort);
    }
}