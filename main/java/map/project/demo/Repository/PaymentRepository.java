package map.project.demo.Repository;

import map.project.demo.Model.Bill;
import map.project.demo.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment , Long> {

    List<Payment> findAllByBill(Bill bill);
}
