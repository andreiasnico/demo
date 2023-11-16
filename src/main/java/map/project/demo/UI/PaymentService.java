package map.project.demo.UI;
import map.project.demo.Model.Payment;
import map.project.demo.Repository.PaymentRepository;
import map.project.demo.Service.Commanders.PaymentCommander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;


@ShellComponent
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @ShellMethod(key = "Payment",value = "list all payments")
    public void findAllPayments(){
        for(Payment payment:paymentRepository.findAll()){
            System.out.println(payment);
        }
    }



}
