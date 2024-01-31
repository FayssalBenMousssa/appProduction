package dev.fenix.application.configuration;

import dev.fenix.application.api.filters.JwtRequestFilter;
import dev.fenix.application.security.model.Activity;
import dev.fenix.application.security.model.User;
import dev.fenix.application.security.repository.ActivityRepository;
import dev.fenix.application.security.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@EnableWebSecurity
public class SecurityConfiguration {

  private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);
  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder(12);
  }
  @Bean
  public SessionRegistry sessionRegistry() {
    return new SessionRegistryImpl();
  }
  @Configuration
  @Order(2)
  public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired UserDetailsService userDetailsService;
    @Autowired ActivityRepository activityRepository;
    @Autowired UserRepository userRepository;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

      http.antMatcher("/**")
          .authorizeRequests()
          .antMatchers("/security/index")
          .hasRole("ADMIN")
          .antMatchers("/user")
          .hasAnyRole("USER", "ADMIN")
          .antMatchers("/business/**")
          .hasAnyRole("USER", "ADMIN")
          .antMatchers("/task/**")
          .hasAnyRole("USER", "ADMIN")
          .antMatchers("/")
          .permitAll()
          .antMatchers("/login")
          .permitAll()
          .and()
          .formLogin()
          .defaultSuccessUrl("/", true);
      http.sessionManagement()
          .invalidSessionUrl("/")
          .maximumSessions(1)
          .sessionRegistry(sessionRegistry())
          .and()
          .sessionFixation()
          .none();
      http.logout()
          .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
          .logoutSuccessHandler(
              new SimpleUrlLogoutSuccessHandler() {
                @Override
                public void onLogoutSuccess(
                    HttpServletRequest request,
                    HttpServletResponse response,
                    Authentication authentication)
                    throws IOException, ServletException {
                  UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                  String username = userDetails.getUsername();

                  User user = userRepository.findOneByUserName(username);
                  //log.info("The user " + username + " has logged out");
                  activityRepository.save(
                      new Activity(
                          user,
                          request.getRemoteAddr(),
                          request.getHeader("referer"),
                          "The user " + username + " has logged out",
                          "INFO",
                          request.getHeader("user-agent"),
                          new Date()));
                  super.onLogoutSuccess(request, response, authentication);
                }
              })
          .permitAll();
      http.formLogin()
          .loginPage("/login")
          .successHandler(
              new AuthenticationSuccessHandler() {
                @Override
                public void onAuthenticationSuccess(
                    HttpServletRequest request,
                    HttpServletResponse response,
                    Authentication authentication)
                    throws IOException, ServletException {
                  UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                  String username = userDetails.getUsername();
                  User user = userRepository.findOneByUserName(username);
                  //log.info("The user " + username + " has logged in.");
                  activityRepository.save(new Activity(user, request.getRemoteAddr(), request.getHeader("referer"), "The user " + username + " has logged in.", "INFO", request.getHeader("user-agent"), new Date()));
                  response.sendRedirect(request.getContextPath());
                }
              })
          .failureHandler(
              new SimpleUrlAuthenticationFailureHandler() {
                @Override
                public void onAuthenticationFailure(
                    HttpServletRequest request,
                    HttpServletResponse response,
                    AuthenticationException exception)
                    throws IOException, ServletException {
                  ///
                  // https://www.codejava.net/frameworks/spring-boot/spring-security-limit-login-attempts-example

                  String username = request.getParameter("username");
                  // String password = request.getParameter("password");
                  String error = exception.getMessage();
                  //log.info("Failed login attempt with username : " + username + ". Reason: " + error);
                  activityRepository.save(
                      new Activity(
                          null,
                          request.getRemoteAddr(),
                          request.getHeader("referer"),
                          "Failed login attempt with username : " + username + ". Reason: " + error,
                          "WARN",
                          request.getHeader("user-agent"),
                          new Date()));
                  super.setDefaultFailureUrl("/login?error");
                  super.onAuthenticationFailure(request, response, exception);
                }
              });
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
      web.ignoring()
          .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/icon/**");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
    }
  }

  @Configuration
  @Order(1)
  public class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
    @Autowired UserDetailsService userDetailsService;
    @Autowired private JwtRequestFilter jwtRequestFilter;
    @Autowired ActivityRepository activityRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    protected void configure(HttpSecurity http) throws Exception {
      http.antMatcher("/api/**")
          .cors()
          .and()
          .csrf()
          .disable()
          .authorizeRequests()
          .antMatchers("/api/authenticate", "/api/security/adduser", "/api/product/images/**")
          .permitAll()
          .antMatchers("/api/**")
          .authenticated()
          .and()
          .httpBasic();


      // activityRepository.save(new Activity(user, request.getRemoteAddr(), request.getHeader("referer"), "The user " + username + " has logged in.", "INFO", request.getHeader("user-agent"), new Date()));

      http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
  }
}
