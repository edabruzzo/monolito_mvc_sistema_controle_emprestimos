package br.com.abruzzo.br.com.abruzzo.tqi_backend_evolution_2021;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Emmanuel Abruzzo
 * @date 05/01/2022
 */
@EnableCaching
@SpringBootApplication
public class SistemaControleEmprestimosApplication {
    public static void main(String[] args) {
        SpringApplication.run(SistemaControleEmprestimosApplication.class, args);
    }
}