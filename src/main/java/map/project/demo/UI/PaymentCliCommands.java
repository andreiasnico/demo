package map.project.demo.UI;

import map.project.demo.Model.DeliveryMethods;
import map.project.demo.Model.Payment;
import map.project.demo.Repository.BillRepository;
import map.project.demo.Repository.PaymentRepository;
import map.project.demo.Service.BillService;
import map.project.demo.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Optional;

/**
 * class that creates the command line interface for the payment
 */
@ShellComponent
public class PaymentCliCommands {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BillService billService;

    /**
     * method that returns all payments
     *
     * @return String of the list of all payments
     */
    @ShellMethod(key = "Payment", value = "show all payments")
    public String allPayments() {
        return paymentService.findAllPayments().toString();
    }

    /**
     * method that adds a payment to the database
     *
     * @param amount   amount paid
     * @param billId   bill id
     * @param bank     bank name
     * @param delivery delivery method
     * @return String of the added Paymnet
     */
    @ShellMethod(key = "add payment", value = "create a payment")
    public String addPayment(
            @ShellOption(value = {"amount"}, help = "volume of entity") Long amount,
            @ShellOption(value = {"billId"}, help = "id of the bill") Long billId,
            @ShellOption(value = {"bank"}, help = "name of the bank") String bank,
            @ShellOption(value = {"delivery"}, help = "delivery method") String delivery
    ) {
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setBank(bank);
        payment.setDeliveryMethods(DeliveryMethods.valueOf(delivery));
        payment.setBill(this.billService.findByBillId(billId).get());
        return this.paymentService.addPayment(payment).toString();
    }

    @ShellMethod(key = "delete payment", value = "delete payment by id")
    public String deletePayment(@ShellOption(value = {"paymentId"}, help = "id of paymnent") Long paymentId) {
        Optional<Payment> payment = this.paymentService.findPaymentById(paymentId);
        if(payment.isEmpty()){
            return "There is no payment with this id";
        }
        this.paymentService.delete(payment.get());
        return "Payment has been deleted";
    }

    /**
     * method that updates a payment
     *
     * @param paymentId payment id
     * @param amount    amount paid
     * @param billId    bill id
     * @return Prompt that the payment has been update and the updated payment
     */
    @ShellMethod(key = "update payment", value = "update payment by id")
    public String updatePayment(@ShellOption(value = {"paymentId"}, help = "id of the payment") Long paymentId,
                                @ShellOption(value = {"amount"}, help = "volume of entity") Long amount,
                                @ShellOption(value = {"billId"}, help = "id of the bill") Long billId) {
        Optional<Payment> payment = this.paymentService.findPaymentById(paymentId);
        if(payment.isEmpty()){
            return "There is no payment with this id";
        }
        payment.get().setBankStatmentId(paymentId);
        payment.get().setAmount(amount);
        payment.get().setBill(this.billService.findByBillId(billId).get());
        this.paymentService.updatePayment(payment.get());
        return "Payment has been updated" + payment.toString();
    }

    /**
     * method that returns all the information of the payment
     *
     * @param paymentId payment id
     * @return String of the Payment
     */
    @ShellMethod(key = "payment info", value = "read payment by id")
    public String readPayment(@ShellOption(value = {"paymentId"}, help = "id of the payment") Long paymentId) {
        Optional<Payment> payment = this.paymentService.findPaymentById(paymentId);
        if(payment.isEmpty()){
            return "There is no payment with this id";
        }
        return payment.get().toString();
    }

}
