package fwdays.delivery;

import fwdays.delivery.domain.Manager;
import fwdays.delivery.persistence.ManagerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
public class DeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryApplication.class, args);
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    ApplicationListener<ContextRefreshedEvent> applicationListener(ManagerRepository managerRepository) {
        return (event) -> {
            Manager manager = new Manager();
            manager.setId(1);
            manager.setName("Peter Dobs");
            manager.setEmail("peter.dobs@gmail.com");
            managerRepository.save(manager);
        };
    }
}
