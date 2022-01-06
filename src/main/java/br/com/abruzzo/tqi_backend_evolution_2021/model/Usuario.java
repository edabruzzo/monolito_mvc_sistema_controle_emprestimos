package br.com.abruzzo.tqi_backend_evolution_2021.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @link https://www.tutorialspoint.com/spring_boot/spring_boot_oauth2_with_jwt.htm
 *
 * @author Emmanuel Abruzzo
 * @date 06/01/2022
 */

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class Usuario implements UserDetails {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "emailAddress")
    private String emailAddress;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private String status;

    @Column(name = "tentativasLogin")
    private Integer loginAttempt;

    @Column(name="enabled")
    private boolean enabled;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        roles.stream().forEach(role -> {

            list.add(new SimpleGrantedAuthority("ROLE_" + role));

        });

        return list;

    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }


}