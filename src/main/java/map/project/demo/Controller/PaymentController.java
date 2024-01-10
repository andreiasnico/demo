package map.project.demo.Controller;

import map.project.demo.Model.Adapters.AdapterFacade;
import map.project.demo.Model.Bill;
import map.project.demo.Model.DeliveryMethods;
import map.project.demo.Model.Payment;
import map.project.demo.Model.dto.PaymentDto;
import map.project.demo.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import map.project.demo.Service.PaymentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService service;

    @Autowired
    private BillService billService;

    @GetMapping("/find-all-payments")
    public List<PaymentDto> findAllPayments() {
        List<Payment> payments = this.service.findAllPayments();
        return payments.stream().map(payment -> (PaymentDto) AdapterFacade.adaptToDto(payment, Payment.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/add-payment")
    public PaymentDto addPayment(@RequestParam Long amount,
                                 @RequestParam Long billId,
                                 @RequestParam String bank,
                                 @RequestParam String deliveryMethod){
        Optional<Bill> bill = this.billService.findByBillId(billId);
        if(bill.isEmpty()){
            return null;
        }
        Payment payment = new Payment();

        payment.setAmount(amount);
        payment.setBank(bank);
        payment.setBill(bill.get());
        payment.setDeliveryMethods(DeliveryMethods.valueOf(deliveryMethod));
        return (PaymentDto) AdapterFacade.adaptToDto(payment , Payment.class);
    }

    @PostMapping("/delete-payment")
    public PaymentDto deletePayment(@RequestParam Long paymentId){
        Optional<Payment> payment = this.service.findPaymentById(paymentId);
        if(payment.isEmpty()){
            return null;
        }

        this.service.deletePaymentById(paymentId);

        return (PaymentDto) AdapterFacade.adaptToDto(payment , Payment.class);
    }


    @PostMapping("update-payment")
    public PaymentDto updatePayment(@RequestParam Long paymentId,
                                    @RequestParam Long amount,
                                    @RequestParam Long billId,
                                    @RequestParam String bank,
                                    @RequestParam String deliveryMethod){
        Optional<Bill> bill = this.billService.findByBillId(billId);
        if(bill.isEmpty()){
            return null;
        }

        Optional<Payment> payment =this.service.findPaymentById(paymentId);
        if(payment.isEmpty()){
            return null;
        }

        payment.get().setAmount(amount);
        payment.get().setBill(bill.get());
        payment.get().setDeliveryMethods(DeliveryMethods.valueOf(deliveryMethod));
        payment.get().setBank(bank);

        this.service.updatePayment(payment.get());
        return (PaymentDto) AdapterFacade.adaptToDto(payment , Payment.class);
    }
}
