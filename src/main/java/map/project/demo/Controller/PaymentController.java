package map.project.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import map.project.demo.Service.PaymentService;

@RestController
@RequestMapping("localhost:8080")
public class PaymentController {
    @Autowired
    private PaymentService service;

    @RequestMapping("/payments")
    public String findAllPayments() {
        return service.findAllPayments().toString();


    }


}
