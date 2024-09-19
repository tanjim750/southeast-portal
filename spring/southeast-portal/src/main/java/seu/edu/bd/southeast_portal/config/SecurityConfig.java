package seu.edu.bd.southeast_portal.config;

import jakarta.annotation.PostConstruct;
import org.apache.coyote.Request;
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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import seu.edu.bd.southeast_portal.filters.JwtFilter;
import seu.edu.bd.southeast_portal.security.Permission;
import seu.edu.bd.southeast_portal.security.Role;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtFilter jwtFilter;

    @Value("${custom.bcrypt.strength}")
    private int bCryptStrength;
    private BCryptPasswordEncoder bCrypt;

    @PostConstruct
    public void init(){
        bCrypt = new BCryptPasswordEncoder(bCryptStrength);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests

                    .requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole(
                            Role.USER.name(),Role.ADMIN.name(),Role.STUFF.name()
                    )
                    .requestMatchers(HttpMethod.POST,"/api/**").hasRole(
                            Role.ADMIN.name()
                    )
                    .requestMatchers(HttpMethod.PUT,"/api/**").hasAnyRole(
                            Role.ADMIN.name(),Role.STUFF.name()
                    )
                    .requestMatchers(HttpMethod.DELETE,"/api/**").hasRole(
                            Role.ADMIN.name()
                    )
                    .requestMatchers(HttpMethod.GET,"/api/**").hasAnyAuthority(
                            Permission.USER_READ.name(),Permission.STUFF_READ.name(),Permission.ADMIN_READ.name()
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
                    .requestMatchers("/auth/login","/auth/create").permitAll()

                    .requestMatchers(HttpMethod.PUT,"/auth/update-role").hasRole(
                            Role.ADMIN.name()
                    )
                    .requestMatchers(HttpMethod.PUT,"/auth/update-role").hasAuthority(
                            Permission.ADMIN_UPDATE.name()
                    )
                    .anyRequest().authenticated()

            )
            .csrf(c -> c.disable())
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

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

}
