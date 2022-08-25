package com.junkfood;

import com.junkfood.entity.Account;
import com.junkfood.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AccountService accountService;

    @Autowired
    BCryptPasswordEncoder pe;


    // cung cấp nguồn dữ liệu đăng nhập
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(username -> {
          try {
              Account user = accountService.findById(username).get();
              String password = pe.encode(user.getPassword());
              String[] roles = user.getAuthorities().stream()
                      .map(er -> er.getRole().getId())
                      .collect(Collectors.toList()).toArray(new String[0]);// đổi sang mảng
              return User.withUsername(username).password(password).roles(roles).build();
          }catch (NoSuchElementException e){
              throw new UsernameNotFoundException(username + "not found");
          }
       });
    }

    // phân quyên sử dụng
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable();

       http.authorizeRequests()
               .antMatchers("/order/**").authenticated()
               .antMatchers("/admin/**").hasAnyRole("STAF","DIRE")
               .antMatchers("/rest/authorities").hasRole("DIRE")
               .anyRequest().permitAll();

       http.formLogin()
               .loginPage("/security/login/form")
               .loginProcessingUrl("/security/login")
               .defaultSuccessUrl("/security/login/success",false)
               .failureUrl("/security/login/error");

       http.rememberMe()
               .tokenValiditySeconds(86400);

       // không có quyền truy xuất
       http.exceptionHandling()
               .accessDeniedPage("/security/unauthoried");

       http.logout()
               .logoutUrl("/security/logoff")// địa chỉ trang đăng xuất
               .logoutSuccessUrl("/security/logoff/success");
    }
///////////////////////////

    // cung cấp nguồn dữ liệu đăng nhập
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService();
//    }
//
//    // phân quyên sử dụng
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests().antMatchers("/**").permitAll()
//                .anyRequest().authenticated().and().httpBasic();
//    }
//
//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails bushan = User.builder().username("bushan").password(pe.encode("password")).roles("USER").build();
//        UserDetails admin = User.builder().username("admin").password(pe.encode("password")).roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(bushan, admin);
//    }

}
