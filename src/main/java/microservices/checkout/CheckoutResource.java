package microservices.checkout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/checkout")

public class CheckoutResource {

    private static Logger logger = LogManager.getLogger(CheckoutResource.class);

    @Autowired
    private CheckoutRepository checkoutRepository;

    @GetMapping("/all")
    public List<Checkout> getAll() {
        return checkoutRepository.findAll();
    }

    @GetMapping("/all/cid/{cid}/caller/{caller}")
    public List<Checkout> getAll(@PathVariable int cid, @PathVariable String caller) {
        logger.info("RES\t{}\t{}\tcheckout\t/all/", cid, caller);
        return checkoutRepository.findAll();
    }

    @GetMapping("/{id}")
    public Checkout getOne(@PathVariable int id) {
        Optional<Checkout> one = checkoutRepository.findById(id);
        return one.get();
    }

    @GetMapping("/{id}/cid/{cid}/caller/{caller}")
    public Checkout getOne(@PathVariable int id, @PathVariable int cid, @PathVariable String caller) {
        logger.info("RES\t{}\t{}\tcheckout\t/{}", cid, caller, id);
        Optional<Checkout> one = checkoutRepository.findById(id);
        return one.get();
    }

    @PostMapping("/create/{id}/cart/{sid}/cid/{cid}/caller/{caller}")
    public void create(@PathVariable int id, @PathVariable int sid, @PathVariable int cid, @PathVariable String caller) {
        logger.info("RES\t{}\t{}\tcheckout\t/create/{}/cart/{}", cid, caller, id, sid);
        Checkout checkout = new Checkout();
        checkout.setId(id);
        checkout.setCartId(sid);

        RestTemplate restTemplate = new RestTemplate();

        //get productid from cart
        int pid = 0;
        try {
            logger.info("REQ\t{}\tcheckout\tcart\t/{}", cid, sid);
            String cartUri = "http://localhost:8093/cart/" + sid + "/cid/" + cid + "/caller/checkout";
            Cart cart = restTemplate.getForObject(cartUri, Cart.class);
            pid = cart.getProductId();
            checkout.setProductId(pid);
        }catch(Exception e){
            logger.info("ERR\t{}\tcheckout\tcart\t/{}\t{}", cid, sid, e.getMessage());
        }

        //get price from product
        double price = 0.0;
        try {
            logger.info("REQ\t{}\tcheckout\tproducts\t/price/{}", cid, pid);
            String productUri = "http://localhost:8092/product/price/" + pid + "/cid/" + cid + "/caller/checkout";
            price = restTemplate.getForObject(productUri, Double.class);
            checkout.setPrice(price);
        }catch(Exception e){
            logger.info("ERR\t{}\tcheckout\tproduct\t/price/{}\t{}", cid, pid, e.getMessage());
        }
        Checkout savedCart = checkoutRepository.save(checkout);
    }

    //TODO put
    //TODO delete

}
