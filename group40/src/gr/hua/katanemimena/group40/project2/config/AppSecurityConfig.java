package gr.hua.katanemimena.group40.project2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)

public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("select username,password, enabled from user where username=?")
				.authoritiesByUsernameQuery("select username, authority from user where username=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/login", "/", "/forgotpassword").permitAll()
				.antMatchers("/admin/*").hasRole("ADMIN").antMatchers("/admin").hasRole("ADMIN")
				.antMatchers("/mga/*").hasAnyRole("ADMIN", "MGA").antMatchers("/mga").hasAnyRole("ADMIN", "MGA")
				.antMatchers("/home", "/newapp").authenticated()
				.anyRequest().authenticated() // all
																												// requests
																												// are
																												// authenticated
				.and().formLogin().loginPage("/login").defaultSuccessUrl("/viewapps", true).and().logout()
				.logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/").permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
		web.ignoring().antMatchers("/api/**");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}