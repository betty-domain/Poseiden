package com.poseiden.springboot.config;

import com.poseiden.springboot.services.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Class to Override Default Spring Configuration
 */
@Configuration
@EnableWebSecurity
public class PoseidenSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    /**
     * Override Default Authentication Provider. Set custon UserDetailsService and PasswordEncoder
     *
     * @return Custom DaoAuthentication Provider
     */
    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    /**
     * Register the custom Authentication Provider into the AuthenticationManagerBuilder
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    /**
     * Define which URL paths sould be secured and which should not.
     *
     * @param http HttpSecurity to configure
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/app/*").permitAll()
                .antMatchers("/user/*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/app/login").
                    failureUrl("/app/login-error").
                    defaultSuccessUrl("/bidList/list", true).permitAll()
                .and().logout().logoutUrl("/app-logout").permitAll()
                .and().exceptionHandling().accessDeniedPage("/403.html");

    }
}

