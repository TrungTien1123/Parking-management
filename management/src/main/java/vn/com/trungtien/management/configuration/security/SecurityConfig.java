package vn.com.trungtien.management.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import vn.com.trungtien.management.exception.CustomExceptionHandler;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    CustomExceptionHandler exceptionHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors(withDefaults())
                .exceptionHandling(handler -> handler
                        .authenticationEntryPoint(exceptionHandler)
                        .accessDeniedHandler(exceptionHandler)

                )
                .authorizeHttpRequests(handler ->handler
                                .antMatchers(HttpMethod.DELETE,"api/building","api/department","api/user","api/vehicle")
                                .hasAnyAuthority("ADMIN")
                                .antMatchers(HttpMethod.POST, "api/vehicle","api/user")
                                .hasAnyAuthority("GUARD", "ADMIN")
                                .antMatchers(HttpMethod.POST, "api/building","api/department")
                                .hasAnyAuthority("GUARD","ADMIN")
                                .antMatchers(HttpMethod.PUT, "api/vehicle","api/user" )
                                .hasAnyAuthority("GUARD")
                                .antMatchers(HttpMethod.PUT,"api/building","api/department")
                                .hasAnyAuthority("ADMIN")
                                .antMatchers("api/auth/**")
                                .permitAll()

                        )
                .httpBasic(withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.applyPermitDefaultValues();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}