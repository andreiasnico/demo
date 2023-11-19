package map.project.demo.Service;

import map.project.demo.Model.Bill;
import map.project.demo.Model.Payment;
import map.project.demo.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BillService billService;

    public List<Payment> findAllPayments(){
        return this.paymentRepository.findAll();
    }

    public Payment addPayment(Payment payment){
        this.paymentRepository.save(payment);

        Optional<Bill> testBill = this.billService.findByBillId(payment.getBill().getBillId());
        this.billService.updateBill(testBill.get());
        payment.getBill().notifyPayments(); // notify to get them updated
        return payment;

    }
}
