package br.com.abruzzo.tqi_backend_evolution_2021.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Emmanuel Abruzzo
 * @date 06/01/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String username;
    private String emailAddress;
    private String password;
    private String status;
    private Integer loginAttempt;
    private List<String> roles = new ArrayList<>();


}
