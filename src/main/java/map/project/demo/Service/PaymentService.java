package map.project.demo.Service;

import map.project.demo.Model.Payment;
import map.project.demo.Repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    public List<Payment> findAllPayments(){
        return this.paymentRepository.findAll();
    }
}
