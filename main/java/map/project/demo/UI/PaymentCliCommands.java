package map.project.demo.UI;

import map.project.demo.Model.Payment;
import map.project.demo.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class PaymentCliCommands {
    @Autowired
    private PaymentRepository paymentRepository;

    @ShellMethod(key = "Payment",value = "show all payments")
    public String allPayments(){return paymentRepository.findAll().toString();
    }
}
