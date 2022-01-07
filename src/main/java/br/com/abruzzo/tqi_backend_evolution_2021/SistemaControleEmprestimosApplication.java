package br.com.abruzzo.tqi_backend_evolution_2021;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import java.util.Arrays;

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

    @Bean
    ModelMapper modelMapper () {return new ModelMapper();}




}
