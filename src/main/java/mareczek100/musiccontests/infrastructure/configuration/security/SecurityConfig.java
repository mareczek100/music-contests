package mareczek100.musiccontests.infrastructure.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String ALL_SUBPAGES = "/**";
    private static final String LOGOUT = "/logout";
    private static final String IMAGES = "/images/**";


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);
        http.requestCache(cache ->
                        cache.requestCache(requestCache))
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .anyRequest().permitAll())
//                                .requestMatchers(MUSIC_CONTESTS_AUTHENTICATION + ALL_SUBPAGES).permitAll()
//                                .requestMatchers(MUSIC_CONTESTS_ERROR).authenticated()
//                                .requestMatchers(IMAGES).authenticated()
//                                .requestMatchers(ADMIN_MAIN_PAGE + ALL_SUBPAGES)
//                                .hasAuthority(RoleEntity.RoleName.ADMIN.name())
//                                .requestMatchers(HEADMASTER_MAIN_PAGE + ALL_SUBPAGES)
//                                .hasAnyAuthority(RoleEntity.RoleName.HEADMASTER.name(), RoleEntity.RoleName.ADMIN.name())
//                                .requestMatchers(TEACHER_MAIN_PAGE + ALL_SUBPAGES)
//                                .hasAnyAuthority(RoleEntity.RoleName.TEACHER.name(), RoleEntity.RoleName.ADMIN.name())
//                                .requestMatchers(STUDENT_MAIN_PAGE + ALL_SUBPAGES)
//                                .hasAnyAuthority(RoleEntity.RoleName.STUDENT.name(), RoleEntity.RoleName.ADMIN.name()))
                .formLogin(formLogin ->
                        formLogin.disable());
//                                .usernameParameter("username")
//                                .passwordParameter("password")
//                                .loginPage(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_LOGIN)
//                                .successHandler(roleDependentAuthenticationSuccessHandler())
//                                .failureUrl(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_FAILURE))
//                .logout(logout ->
//                        logout
//                                .logoutUrl(MUSIC_CONTESTS_AUTHENTICATION + LOGOUT)
//                                .logoutSuccessUrl(MUSIC_CONTESTS_AUTHENTICATION + LOGOUT)
//                                .invalidateHttpSession(true)
//                                .clearAuthentication(true)
//                                .deleteCookies("JSESSIONID")
//                                .permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService
    ) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http,
            AuthenticationProvider authenticationProvider
    ) throws Exception {
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authenticationProvider)
                .build();
    }
    @Bean
    public AuthenticationSuccessHandler roleDependentAuthenticationSuccessHandler() {
        return new RoleDependentAuthenticationSuccessHandler();
    }

}