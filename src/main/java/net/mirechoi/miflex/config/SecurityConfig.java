package net.mirechoi.miflex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import net.mirechoi.miflex.mapper.UsersMapper;
import net.mirechoi.miflex.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
	
	private final UsersMapper usersMapper;
	
    public SecurityConfig(UsersMapper userMapper) {
    	this.usersMapper = userMapper;
    }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http
		.csrf()
		.and()
		.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/member/**").hasAnyRole("ADMIN", "USER")
		.antMatchers("/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		   .loginPage("/login")
		   .usernameParameter("userid")
		   .passwordParameter("userpass")
		   .defaultSuccessUrl("/", true)
		   .failureUrl("/login?error=true")
		   .permitAll()
		.and()
		.logout()
		   .logoutUrl("/logout")
		   .logoutSuccessUrl("/")
		   .permitAll();
		
		return http.build();
		
	}
	
	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 * 
	 * @Bean public CustomUserDetailService customUserDetailsService() { return new
	 * CustomUserDetailService(usersMapper);
	 */
	/*}*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomUserDetailService customUserDetailsService() {
        return new CustomUserDetailService(usersMapper);
    }

	
	//인증 관리자 설정
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(customUserDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return new ProviderManager(authProvider);
	}
}

