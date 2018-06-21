package microservices.checkout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CheckoutResource {

    private static Logger logger = LogManager.getLogger(CheckoutResource.class);

    @Autowired
    private CheckoutRepository checkOutReposity;

    @GetMapping("/checkouts")
    public List<Checkout> getAll() {
        return checkOutReposity.findAll();
    }

    @GetMapping("/checkouts/{id}")
    public Checkout getOne(@PathVariable int id) {
        Optional<Checkout> one = checkOutReposity.findById(id);
        return one.get();
    }

    @PostMapping("/checkouts/")
    public void create(@RequestBody Checkout checkout) {
        Checkout savedCheckout = checkOutReposity.save(checkout);
    }

    @PostMapping("/checkouts/{id}/cid/{cid}/caller/{caller}")
    public void createByID(@PathVariable int id, @PathVariable int cid, @PathVariable String caller) {
        logger.info("CALL\t{}\t{}\tCheckout\tProduct/getPrice", cid, caller);
    }


    @PutMapping("/checkouts/{id}")
    public void update(@RequestBody Checkout checkout, @PathVariable int id) {
        checkout.setId(id);
        checkOutReposity.save(checkout);
    }

    @DeleteMapping("/checkouts/{id}")
    public void delete(@PathVariable int id) {
        checkOutReposity.deleteById(id);
    }

}
