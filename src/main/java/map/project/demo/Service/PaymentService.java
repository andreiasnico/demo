package map.project.demo.Service;

import map.project.demo.Model.Payment;
import map.project.demo.Repository.PaymentRepository;
import map.project.demo.Service.Commanders.PaymentCommander;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    public List<Payment> findAllPayments(){
        return this.paymentRepository.findAll();
    }
    public void addPayment(Payment payment){
        this.paymentRepository.save(payment);
        PaymentCommander paymentCommander = PaymentCommander.getInstance(this); //also a little overkill
        paymentCommander.execute();

    }
    public void deletePayment(Payment payment){
        this.paymentRepository.delete(payment);
        PaymentCommander paymentCommander = PaymentCommander.getInstance(this); //also a little overkill
        paymentCommander.execute();
    }

    public void updatePayment(Payment payment){
        this.paymentRepository.save(payment);
        PaymentCommander paymentCommander = PaymentCommander.getInstance(this); //also a little overkill
        paymentCommander.execute();
    }

}
