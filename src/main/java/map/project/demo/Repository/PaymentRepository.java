package map.project.demo.Repository;

import map.project.demo.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment , Long> {

        Optional<Payment> findByBankStatmentId(Long id);

        void deleteByBankStatmentId(Long id);


        //Todo further implementation of all we need
}
