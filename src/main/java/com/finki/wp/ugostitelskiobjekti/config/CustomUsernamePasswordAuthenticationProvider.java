package com.finki.wp.ugostitelskiobjekti.config;


import com.finki.wp.ugostitelskiobjekti.Service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomUsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public CustomUsernamePasswordAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    //se povikuva vo onoj moment koga se pravi post baranje do /login
    //informaciite od username i pass se naogjaat vo authentication
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        if ("".equals(username) || "".equals(password)) {
           throw new BadCredentialsException("Invalid Credentials");
        }
        //ajde da proverime dali postoi korisnikot vo bazata
        UserDetails userDetails = this.userService.loadUserByUsername(username);
        //da proverime dali passwordite se match,t.e heshiranata vrednost vo baza
        //mora passwordEncoder da se koristi
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
          throw new BadCredentialsException("Invalid Credentials");

//ako pasvordot koj e tekovno vnesen ne se sofpagja so onoj od bazata
        }
        //authorities koj se na user-ot ,t.e roles
return new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(),userDetails.getAuthorities());//do ovde so ova sme zav so custom auth provider

    }

    //proveruva dali a klas e od tipot...
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
    //celta e da gi chita podatocite od nashata tabela
}
