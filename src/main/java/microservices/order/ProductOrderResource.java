package microservices.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductOrderResource {

    private static Logger logger = LogManager.getLogger(ProductOrderResource.class);

    @Autowired
    private ProductOrderReposity productOrderReposity;

    @GetMapping("/orders")
    public List<Purchase> getAll() {
        return productOrderReposity.findAll();
    }

    @GetMapping("/orders/cid/{cid}/caller/{caller}")
    public List<Purchase> getAll(@PathVariable int cid, @PathVariable String caller) {
        logger.info("CALL\t{}\t{}\tOrders\t/orders", cid, caller);
        return productOrderReposity.findAll();
    }

    @GetMapping("/orders/{id}")
    public Purchase getOne(@PathVariable int id) {
        Optional<Purchase> one = productOrderReposity.findById(id);
        return one.get();
    }

    @DeleteMapping("/orders/{id}")
    public void delete(@PathVariable int id) {
        productOrderReposity.deleteById(id);
    }

    @PostMapping("/orders")
    public void create(@RequestBody Purchase Purchase) {
        Purchase savedPurchase = productOrderReposity.save(Purchase);
    }

    @PutMapping("/orders/{id}")
    public void update(@RequestBody Purchase Purchase, @PathVariable int id) {
        Optional<Purchase> a = productOrderReposity.findById(id);

        Purchase.setPurchaseId(id);
        productOrderReposity.save(Purchase);
    }
}
