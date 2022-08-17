package com.banquemisr;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

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
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
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