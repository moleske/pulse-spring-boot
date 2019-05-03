package org.apache.geode.tools.pulse.internal;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;

import org.apache.geode.tools.pulse.internal.security.GemFireAuthenticationProvider;
import org.apache.geode.tools.pulse.internal.security.LogoutHandler;

@Configuration
@EnableWebSecurity
@ServletComponentScan
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    ExceptionMappingAuthenticationFailureHandler
        authenticationFailureHandler =
        new ExceptionMappingAuthenticationFailureHandler();//todo set mappings
    http.csrf().disable().httpBasic().disable()
        .authorizeRequests()
          .antMatchers("/*").permitAll();
//          .antMatchers("/authenticateUser").permitAll()
//          .antMatchers("/pulseVersion").permitAll()
//          .antMatchers("/accessDenied*").authenticated()
//          .antMatchers("/dataBrowser*").hasAnyRole("CLUSTER:READ", "DATA:READ")//TODO this need to be and michael
//          .antMatchers("/getQueryStatisticsGridModel/*").hasAnyRole("CLUSTER:READ", "DATA:READ")//TODO this need to be and michael
//          .antMatchers("/static").hasRole("CLUSTER:READ")
//        .and().formLogin()
//          .loginPage("/login.html")
//          .failureHandler(authenticationFailureHandler)
//          .defaultSuccessUrl("/clusterDetail.html")
//        .and().headers()
//          .frameOptions().deny()
//        .and().logout()
//          .logoutUrl("/clusterLogout").logoutSuccessHandler(new LogoutHandler("/login.html"));
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
//        .authenticationProvider(new GemFireAuthenticationProvider())
        .inMemoryAuthentication().withUser("admin").password("admin").roles("CLUSTER:READ", "DATA:READ");
  }
}
