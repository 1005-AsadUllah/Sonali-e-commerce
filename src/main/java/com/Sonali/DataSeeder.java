package com.Sonali;

import com.Sonali.entity.Role;
import com.Sonali.entity.User;
import com.Sonali.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                User admin = new User();
                admin.setEmail("admin@sonali.com");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);

                User customer = new User();
                customer.setEmail("customer@sonali.com");
                customer.setPassword(encoder.encode("cust123"));
                customer.setRole(Role.CUSTOMER);
                userRepository.save(customer);
            }
        };
    }
}
