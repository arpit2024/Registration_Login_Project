package org.example.registration_login_project.configs;

//Make this class as a spring java Based configuration by using @Configuration annotation
//Now we can define spring bean
import org.aspectj.weaver.ast.And;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//in order to enable spring security support in a spring MVC application, we have to use add enablewebSecurity Annotation
@EnableWebSecurity
public class SpringSecurity {

    //Configure the SecurityFilterChain- a chain of filters that are responsible for processing the request
    @Bean//disable CSRF feature as we are not using it
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .anyRequest().authenticated()
                )
    //we can use this form login method to configure the custom login page
    //well form login method provides a form object we can use it to configure spring security for custom login form
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/users")//once successfully logged in we will be navigated to users page-default
                        .permitAll()
                );
        return http.build();
    }
}
/*
    So here just type form and then lambda symbol and then form object.
    It has a login page method and let's pass the URL slash login. So this will react to the custom logging page.
    And next, let's configure the log in processing URL for that,and then pass slash logging.
    So this slash logging URL, basically spring security, use this slash log in to process the login form.

    Next, let's call default success url.Well, once user successfully logged in,
    then we need to navigate that user to some default page.let's call default success.

    Next, we'll allow logging user to navigate to the users page.For that, let's configure slash users URL.
    call permit all method to give access to all the users.

    And finally, let's build this HTTP Object by using build method.
    So this will return security filter chain object.         */
