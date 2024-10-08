package org.example.registration_login.configs;

/* some imp points:-
- Well, spring security six onwards, we don't have to manually set the user details, service and
password in order to authentication manager.
- Spring security will automatically set user details, service and password encoded objects to the authentication
- So we just have to make sure that user details service bean and the password bean exists in the spring application context.
- spring security will automatically inject these beans to the authentication manager.
We don't have to manually set these beans.
 */


//Make this class as a spring java Based configuration by using @Configuration annotation
//Now we can define spring bean
import org.example.registration_login.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.PasswordManagementDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
//in order to enable spring security support in a spring MVC application, we have to use add enablewebSecurity Annotation
@EnableWebSecurity
public class SpringSecurity {

    //we need to inject user details service in this spring security configuration class
    //So here let me create the instance variable private user details service
//    @Autowired
//    private UserDetailsService userDetailsService;
//can be commented because-no manual setting of user details service and password encoder is required.(explained above)

    /*
     if you go to user details-user service class, we have stored the plain text password in our database
    But we have to encrypt this password before we save to the database.
    Or we'll use spring security provided bcrypt password encoder.
    So go to spring security. And here let's create a BcryptPasswordEncoder.*/
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    //Configure the SecurityFilterChain- a chain of filters that are responsible for processing the request
    @Bean//disable CSRF feature as we are not using it
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    /*
    we need to allow all the users to register to our application.
    if I click on register URL, it will redirect to the login page. We have to allow all the users to
    navigate to the register/other URL page
    configure spring security in such a way that will allow all the users to access homepage in the register page.*/

        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authorizeRequests -> authorizeRequests

    /* we want to provide access that is registered slash star star and then let's call permit all method.
    It means that we are allowing all the requests.It starts with slash register and here we are providing star,
    star because if we go to our Auth controller
    we have one of the request like /register/save- right? So in order to handle this kind of request, we have to provide /**.
    It means we allow all the request. */

                .requestMatchers("/register/**").permitAll()//so here for register and index page everyone can access
                .requestMatchers("/index").permitAll()
                .requestMatchers("/users").hasRole("ADMIN")//but for users page only admin-role can access-so changed the role to admin in app.properties file
                  //.anyRequest().authenticated()
                )



    //we can use this form login method to configure the custom login page
    //well form login method provides a form object we can use it to configure spring security for custom login form

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

                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/users")//once successfully logged in we will be navigated to users page-default
                        .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
   /*
   once you are logged into our application , you can see the log out link. So logged in user can use this log
    link to log out from this application.
    we have to add the log of success message at the top of this in form.
    Well, in order to get this log parameter in the URL, we have to configure the spring security forlog out.

    let's go ahead and click on the logout You can see in a URL we got a question mark log parameter
    So in order to get this log parameter, we have configured a spring security for log
    Next, we can use this log parameter to add a success log message in this login form.    */

        return http.build();
    }

    //Public method to set user details, service and password encoder bean to Authentication manager.
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {// we will use basically authentication manager builder to build that Authentication manager object.
//       builder.userDetailsService(userDetailsService)
//               .passwordEncoder(passwordEncoder());
//    }
    // no need to manually set user details service and password encoder in authentication manager.(explained above)

}

