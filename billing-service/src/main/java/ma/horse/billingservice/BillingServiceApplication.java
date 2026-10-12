package ma.horse.billingservice;

import ma.horse.billingservice.entities.Bill;
import ma.horse.billingservice.entities.ProductItem;
import ma.horse.billingservice.feign.CustomerServiceRestClient;
import ma.horse.billingservice.repository.BillRepository;
import ma.horse.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository) {
        return args -> {
            List<Long> customerIds = List.of(1L, 2L, 3L);
            List<Long> productIds = List.of(1L, 2L, 3L);
            customerIds.forEach(customerId -> {
                Bill bill = new Bill();
                bill.setBillingDate(new Date());
                bill.setCustomerId(customerId);
                billRepository.save(bill);
                productIds.forEach(productId -> {
                    ProductItem productItem = new ProductItem();
                    productItem.setProductId(productId);
                    productItem.setPrice(1000*Math.random()*600);
                    productItem.setQuantity(1+ new Random().nextInt(20));
                    productItem.setBill(bill);
                    productItemRepository.save(productItem);
                });
            });
        };
    }
}
