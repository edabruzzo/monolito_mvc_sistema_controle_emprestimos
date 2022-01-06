package br.com.abruzzo.tqi_backend_evolution_2021.config;


import org.springframework.context.annotation.Bean;
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
 *
 * @link https://stackoverflow.com/questions/52862769/basic-jdbc-authentication-authorization-not-working
 *
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


    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {


        auth.jdbcAuthentication().dataSource(this.dataSource).usersByUsernameQuery("select username, password, 'true' as enabled from users where username = ?")
                .authoritiesByUsernameQuery("select username, authority from authorities where username = ?")
                .passwordEncoder(encoder);
    }




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(this.encoder);

        String cpfAdmin = "99999999999";
        String senhaCriptografada = this.encoder.encode("9999");

        Usuario usuario = this.autenticacaoUsuarioRepository.findByUsername(cpfAdmin);

        if(usuario == null){

            UserDetails userDetails = User.builder()
                    .username(cpfAdmin)
                    .password(senhaCriptografada)
                    .roles(String.valueOf(Role.FUNCIONARIO),String.valueOf(Role.SUPER_ADMIN))
                    .build();

            List<Role> roles = new ArrayList<>();
            roles.add(Role.FUNCIONARIO);
            roles.add(Role.SUPER_ADMIN);

            usuario = new Usuario();
            usuario.setUsername(cpfAdmin);
            usuario.setPassword(senhaCriptografada);
            usuario.setRoles(roles);
            usuario.setEnabled(true);

            this.autenticacaoUsuarioRepository.save(usuario);

        }




    }



}

