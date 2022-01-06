package br.com.abruzzo.tqi_backend_evolution_2021.config;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


import br.com.abruzzo.tqi_backend_evolution_2021.model.Role;
import br.com.abruzzo.tqi_backend_evolution_2021.model.Usuario;
import br.com.abruzzo.tqi_backend_evolution_2021.repository.AutenticacaoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
                        .defaultSuccessUrl("/cliente", true)
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

        String cpfAdmin = "99999999999";
        String senhaCriptografada = encoder.encode("9999");

        Usuario usuario = this.autenticacaoUsuarioRepository.findByUsername(cpfAdmin);

        if(usuario == null){

            UserDetails userDetails = User.builder()
                    .username(cpfAdmin)
                    .password(senhaCriptografada)
                    .roles(String.valueOf(Role.FUNCIONARIO),String.valueOf(Role.SUPER_ADMIN))
                    .build();

            List<Role> papeis = new ArrayList<>();
            papeis.add(Role.FUNCIONARIO);
            papeis.add(Role.SUPER_ADMIN);

            usuario = new Usuario();
            usuario.setUsername(cpfAdmin);
            usuario.setPassword(senhaCriptografada);
            usuario.setPapeis(papeis);
            usuario.setEnabled(true);

            this.autenticacaoUsuarioRepository.save(usuario);

        }




    }



}

