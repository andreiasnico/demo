package map.project.demo.Service;

import map.project.demo.Model.Bill;
import map.project.demo.Model.Payment;
import map.project.demo.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        Optional<Bill> testBill = this.billService.findByBillId(payment.getBill().getBankStatmentId());
        this.billService.updateBill(testBill.get());
        payment.getBill().notifyPayments(); // notify to get them updated
        return payment;
    }

    public void deletePaymentById(Long paymentId){
        this.paymentRepository.deleteByBankStatmentId(paymentId);
    }

    public Payment findPaymentById(Long id){
        return this.paymentRepository.findByBankStatmentId(id);
    }

    public void delete(Payment payment){
        this.paymentRepository.delete(payment);
    }

    public void updatePayment(Payment payment) {
        Payment updatedPayment = paymentRepository.findByBankStatmentId(payment.getBankStatmentId());
        updatedPayment.setAmount(payment.getAmount());
        updatedPayment.setBill(payment.getBill());
        paymentRepository.save(updatedPayment);
    }

    public void readPayment(Payment payment) {
        paymentRepository.findById(payment.getBankStatmentId());
    }


}
