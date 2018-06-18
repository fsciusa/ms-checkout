package microservices.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductOrderResource {

    @Autowired
    private ProductOrderReposity productOrderReposity;

    @GetMapping("/orders")
    public List<Purchase> getAll() {
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
