package com.ljh.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * SecurityConfig
 * <p><a href="https://blog.csdn.net/weixin_59216829/article/details/130098599">Spring Security 6.0 弃用 WebSecurityConfigurationAdapter</a>
 *
 * @author Arsenal
 * created on 2020/1/2 23:39
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig
        // extends WebSecurityConfigurerAdapter
{

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "index").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(withDefaults())
                .logout(LogoutConfigurer::permitAll);
        return httpSecurity.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("demo").password(new BCryptPasswordEncoder().encode("demo")).roles("USER")
                .and()
                .withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("ADMIN");
    }
}
