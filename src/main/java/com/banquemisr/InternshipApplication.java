package com.banquemisr;

import com.thetransactioncompany.cors.CORSFilter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class InternshipApplication {

    public static void main(String... args) {
        SpringApplication.run(InternshipApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new CORSFilter());
        registrationBean.setName("CORS FIlter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
    /*@Bean
    CommandLineRunner run(roleImpService Service) {
        return args -> {
            Service.saveRole(new Role(null, "ROLE_USER"));
            Service.saveRole(new Role(null, "ROLE_ADMIN"));
            //Service.saveRole(new Role(null, "ROLE_MANAGER"));
            //Service.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

//            Service.createNewUser(new UserDTO(null, "John Travolta", "John", "1234"));
//            Service.createNewUser(new UserDTO(null, "Will Smith", "Will", "1234"));
//            Service.createNewUser(new UserDTO(null, "Jim Carry", "Jim", "1234"));
//            Service.createNewUser(new UserDTO(null, "Arnold Schwarzenegger", "Arnold", "1234"));

            Service.addRoleToUser("John", "ROLE_USER");
            //Service.addRoleToUser("John", "ROLE_MANAGER");
            //Service.addRoleToUser("Will", "ROLE_MANAGER");
            Service.addRoleToUser("Jim", "ROLE_ADMIN");
            //Service.addRoleToUser("Arnold", "ROLE_SUPER_ADMIN");
            //Service.addRoleToUser("Arnold", "ROLE_ADMIN");
            //Service.addRoleToUser("Arnold", "ROLE_USER");

        };
    }*/
}