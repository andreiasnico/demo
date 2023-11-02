package map.project.demo.Repository;

import map.project.demo.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment , Long> {

        Payment findByPaymentId(Long id);

        //Todo further implementation of all we need
}
