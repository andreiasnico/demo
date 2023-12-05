package map.project.demo.UI;

import map.project.demo.Model.Payment;
import map.project.demo.Repository.BillRepository;
import map.project.demo.Repository.PaymentRepository;
import map.project.demo.Service.BillService;
import map.project.demo.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class PaymentCliCommands {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BillService billService;
    @ShellMethod(key = "Payment",value = "show all payments")
    public String allPayments(){return paymentService.findAllPayments().toString();
    }

    @ShellMethod(key = "add payment" , value = "create a payment")
    public String addPayment(
                             @ShellOption(value = {"amount"}, help = "volume of entity") Long amount,
                             @ShellOption(value = {"billId"}, help = "id of the bill") Long billId
                             ){
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setBill(this.billService.findByBillId(billId).get());
        return this.paymentService.addPayment(payment).toString();
    }

    @ShellMethod(key = "delete payment" , value = "delete payment by id")
    public String deletePayment(@ShellOption(value = {"paymentId"} , help = "id of paymnent") Long paymentId){
        Payment payment = this.paymentService.findPaymentById(paymentId);
        this.paymentService.delete(payment);
        return "Payment has been deleted";
    }

    @ShellMethod(key = "update payment" , value="update payment by id")
    public String updatePayment(@ShellOption(value = {"paymentId"} , help = "id of the payment") Long paymentId,
                                @ShellOption(value = {"amount"}, help = "volume of entity") Long amount,
                                @ShellOption(value = {"billId"}, help = "id of the bill") Long billId){
        Payment payment = new Payment();
        payment.setPaymentId(paymentId);
        payment.setAmount(amount);
        payment.setBill(this.billService.findByBillId(billId).get());
        this.paymentService.updatePayment(payment);
        return "Payment has been updated";
    }

    @ShellMethod(key = "read payment" , value="read payment by id")
    public String readPayment(@ShellOption(value = {"paymentId"} , help = "id of the payment") Long paymentId){
        Payment payment = new Payment();
        payment.setPaymentId(paymentId);
        this.paymentService.readPayment(payment);
        return "Payment has been read";
    }

}
