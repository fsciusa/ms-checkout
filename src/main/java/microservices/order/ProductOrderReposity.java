package microservices.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderReposity extends JpaRepository<Purchase, Integer> {
}
