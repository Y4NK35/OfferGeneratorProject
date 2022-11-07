package offerGenerator;

import offerGenerator.component.CustomDaoAuthenticationProvider;
import offerGenerator.service.implementation.JpaUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug=true)
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    CustomDaoAuthenticationProvider customDaoAuthenticationProvider;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(customDaoAuthenticationProvider);
        http
                .authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/confirm_account").permitAll()
                .antMatchers("/sign_up").permitAll()
                .antMatchers("/admin_panel").hasAuthority("ADMIN")
                .anyRequest().authenticated().
                and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/user_panel", true)
                .and()
                .logout()
                .logoutUrl("/user_logout")
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("cokkies");

        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web) -> web.ignoring().antMatchers("/images/**","/resources/**", "/js/**", "/webjars/**");

    }
}
