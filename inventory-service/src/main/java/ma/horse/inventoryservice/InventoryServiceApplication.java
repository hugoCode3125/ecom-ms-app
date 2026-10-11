package ma.horse.inventoryservice;

import ma.horse.inventoryservice.entities.Product;
import ma.horse.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository repository) {
		return args -> {
			repository.save(Product.builder()
					.name("Computer")
					.price(1599)
					.quantity(2)
					.build());
			repository.save(Product.builder()
					.name("Smart Phone")
					.price(5400)
					.quantity(1)
					.build());
			repository.save(Product.builder()
					.name("winner-150")
					.price(159200)
					.quantity(3)
					.build());
		};
	}
}
