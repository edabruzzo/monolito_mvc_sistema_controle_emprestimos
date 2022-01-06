package br.com.abruzzo.tqi_backend_evolution_2021.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="emailAddress")
    private String emailAddress;

    @Column(name="password")
    private String password;

    @Column(name="status")
    private String status;

    @Column(name="tentativasLogin")
    private Integer loginAttempt;

    @ElementCollection(fetch= FetchType.EAGER)
    @Builder.Default
    private List<Role> roles = new ArrayList<>();
    
}
