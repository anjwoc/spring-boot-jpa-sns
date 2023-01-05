package me.anjwoc.sns.config;

import lombok.RequiredArgsConstructor;
import me.anjwoc.sns.domain.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig  {
    private final UserService userService;

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .formLogin().disable()
            .httpBasic().disable()
//            .apply(new CustomFilterDsl())
//            .and()
            .authorizeHttpRequests(auth ->
                    {
                        try {
                            auth
                                    .requestMatchers("/h2-console/**", "/api/**")
//                                    .mvcMatchers("/h2-console/**", "/products/**")
                                    .permitAll()
                                    .anyRequest()
                                    .authenticated()
                                    .and()
                                    .csrf()
                                    .ignoringRequestMatchers("/h2-console/**")
//                                    .ignoringAntMatchers("/h2-console/**")
                                    .disable();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
            );

        http.headers().frameOptions().disable();

        return http.build();
    }
}
