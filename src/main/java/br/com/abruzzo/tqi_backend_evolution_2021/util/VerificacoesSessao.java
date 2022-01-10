package br.com.abruzzo.tqi_backend_evolution_2021.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Emmanuel Abruzzo
 * @date 10/01/2022
 */
public class VerificacoesSessao {

    static Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    public static boolean verificaSeUsuarioLogadoCliente() {

        return authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("CLIENTE"));
    }

    public static String verificaEmailUsuarioLogado() {
        return authentication.getName();
    }
}
