package map.project.demo.Controller;

import map.project.demo.Model.Adapters.AdapterFacade;
import map.project.demo.Model.Payment;
import map.project.demo.Model.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import map.project.demo.Service.PaymentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService service;

    @RequestMapping("/find-all-payments")
    public List<PaymentDto> findAllPayments() {
        List<Payment> payments = this.service.findAllPayments();
        return payments.stream().map(payment -> (PaymentDto) AdapterFacade.adaptToDto(payment, Payment.class))
                .collect(Collectors.toList());
    }


}
