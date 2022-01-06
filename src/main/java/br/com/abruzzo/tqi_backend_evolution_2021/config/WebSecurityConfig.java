package br.com.abruzzo.tqi_backend_evolution_2021.config;

import br.com.abruzzo.tqi_backend_evolution_2021.model.Role;
import br.com.abruzzo.tqi_backend_evolution_2021.model.Usuario;
import br.com.abruzzo.tqi_backend_evolution_2021.repository.AutenticacaoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Emmanuel Abruzzo
 * @date 06/01/2022
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/home/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl(String.format("/home"), true)
                        .permitAll()
                )
                .logout(logout -> {
                    logout.logoutUrl("/logout")
                            .logoutSuccessUrl("/home");
                }).csrf().disable();
    }


    @Autowired
    AutenticacaoUsuarioRepository autenticacaoUsuarioRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder);

        /*
        UserDetails usuarioInicial = User.withDefaultPasswordEncoder()
                .username("99999999999")
                .password("9999")
                .roles(String.valueOf(Role.FUNCIONARIO))
                .roles(String.valueOf(Role.SUPER_ADMIN))
                .build();

         */

        Usuario usuario = new Usuario();
        usuario.setUsername("9999999999");
        usuario.setPassword("9999");
        List<Role> roles = new ArrayList<>();
        roles.add(Role.FUNCIONARIO);
        roles.add(Role.SUPER_ADMIN);
        usuario.setRoles(roles);

        this.autenticacaoUsuarioRepository.save(usuario);

    }



}

