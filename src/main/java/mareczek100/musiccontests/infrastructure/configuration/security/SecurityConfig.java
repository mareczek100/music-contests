package mareczek100.musiccontests.infrastructure.configuration.security;

import mareczek100.musiccontests.infrastructure.database.entity.security.RoleEntity;
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

import static mareczek100.musiccontests.api.controller.AdminController.ADMIN_MAIN_PAGE;
import static mareczek100.musiccontests.api.controller.HeadmasterController.HEADMASTER_MAIN_PAGE;
import static mareczek100.musiccontests.api.controller.MainPageController.*;
import static mareczek100.musiccontests.api.controller.StudentController.STUDENT_MAIN_PAGE;
import static mareczek100.musiccontests.api.controller.TeacherController.TEACHER_MAIN_PAGE;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String ALL_SUBPAGES = "/**";


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                        .requestMatchers(MUSIC_CONTESTS_AUTHENTICATION + ALL_SUBPAGES).permitAll()
                        .requestMatchers(ADMIN_MAIN_PAGE + ALL_SUBPAGES)
                        .hasAuthority(RoleEntity.RoleName.ADMIN.name())
                        .requestMatchers(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_PROCESS)
                        .hasAnyAuthority(RoleEntity.RoleName.ADMIN.name(), RoleEntity.RoleName.HEADMASTER.name(),
                                RoleEntity.RoleName.TEACHER.name(), RoleEntity.RoleName.STUDENT.name())
                        .requestMatchers(HEADMASTER_MAIN_PAGE + ALL_SUBPAGES)
                        .hasAuthority(RoleEntity.RoleName.HEADMASTER.name())
                        .requestMatchers(TEACHER_MAIN_PAGE + ALL_SUBPAGES)
                        .hasAuthority(RoleEntity.RoleName.TEACHER.name())
                        .requestMatchers(STUDENT_MAIN_PAGE + ALL_SUBPAGES)
                        .hasAuthority(RoleEntity.RoleName.STUDENT.name()))
                .formLogin(formLogin ->
                        formLogin.usernameParameter("username")
                                .passwordParameter("password")
                                .loginPage(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_LOGIN)
                                .failureUrl(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_FAILURE)
                                .loginProcessingUrl(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_PROCESS)
                );
        http.logout(logout ->
                logout.invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutUrl("/authentication/logout")
                        .deleteCookies("JSESSIONID")
                        .permitAll());
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

}
