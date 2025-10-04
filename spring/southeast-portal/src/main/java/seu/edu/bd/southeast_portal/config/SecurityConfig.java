package seu.edu.bd.southeast_portal.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import seu.edu.bd.southeast_portal.exceptionHandler.CustomAuthenticationEntryPoint;
import seu.edu.bd.southeast_portal.filters.JwtFilter;
import seu.edu.bd.southeast_portal.authorization.Permission;
import seu.edu.bd.southeast_portal.authorization.Role;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Value("${custom.bcrypt.strength}")
    private int bCryptStrength;
    private BCryptPasswordEncoder bCrypt;

    @PostConstruct
    public void init(){
        bCrypt = new BCryptPasswordEncoder(bCryptStrength);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        Object CustomAuthenticationEntryPoint;

        http
            .cors(Customizer.withDefaults())
            .csrf(c -> c.disable())
            .authorizeHttpRequests((requests) -> requests

                    .requestMatchers(HttpMethod.GET)
                    .permitAll()
                    .requestMatchers("/auth/login","/auth/create")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/**").hasRole(
                            Role.ADMIN.name()
                    )
                    .requestMatchers(HttpMethod.PUT,"/api/**").hasAnyRole(
                            Role.ADMIN.name(),Role.STUFF.name()
                    )
                    .requestMatchers(HttpMethod.DELETE,"/api/**").hasRole(
                            Role.ADMIN.name()
                    )
                    .requestMatchers(HttpMethod.POST,"/api/**").hasAuthority(
                            Permission.ADMIN_CREATE.name()
                    )
                    .requestMatchers(HttpMethod.PUT,"/api/**").hasAnyAuthority(
                            Permission.STUFF_UPDATE.name(),Permission.ADMIN_UPDATE.name()
                    )
                    .requestMatchers(HttpMethod.DELETE,"/api/**").hasAuthority(
                            Permission.ADMIN_DELETE.name()
                    )
                    .requestMatchers(HttpMethod.PUT,"/auth/update-role").hasRole(
                            Role.ADMIN.name()
                    )
                    .requestMatchers(HttpMethod.PUT,"/auth/update-role").hasAuthority(
                            Permission.ADMIN_UPDATE.name()
                    )
                    .anyRequest().authenticated()

            )

            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(exceptions ->
                        exceptions.authenticationEntryPoint(customAuthenticationEntryPoint) // Set the custom entry point
                );

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCrypt);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*")); // Set allowed origin
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // Allow specific HTTP methods
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true); // Enable credentials (cookies, authorization headers)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
