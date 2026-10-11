package ma.horse.customerservice;

import ma.horse.customerservice.entities.Customer;
import ma.horse.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository){
        return args -> {
            customerRepository.save(Customer.builder()
                    .name("hugo")
                    .email("hugo@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("fawzi")
                    .email("fawzi@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("Djelika")
                    .email("djelika@gmail.com")
                    .build());
        };
    }
}
