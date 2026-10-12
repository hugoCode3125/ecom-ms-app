package ma.horse.billingservice.web;

import ma.horse.billingservice.entities.Bill;
import ma.horse.billingservice.feign.CustomerServiceRestClient;
import ma.horse.billingservice.feign.InventoryServiceRestClient;
import ma.horse.billingservice.model.Customer;
import ma.horse.billingservice.repository.BillRepository;
import ma.horse.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BillRestController {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerServiceRestClient customerServiceRestClient;
    @Autowired
    private InventoryServiceRestClient inventoryServiceRestClient;

    @GetMapping("/bills/{id}")
    public Bill getBillById(@PathVariable Long id){
        Bill bill = billRepository.findById(id).get();
        Customer customer = customerServiceRestClient.findCustomerById(bill.getCustomerId());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(
                    inventoryServiceRestClient.findProductById(productItem.getProductId())
            );
        });
        return  bill;
    }
}
