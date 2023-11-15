package map.project.demo.Service.Builders;

import map.project.demo.Model.DeliveryMethods;
import map.project.demo.Service.PaymentService;

public class PaymentBuilder implements Builder{

    private PaymentService paymentService;

    private void setDeliveryMethod(){
        this.paymentService.findAllPayments().stream().filter(payment -> payment.getDeliveryMethod() != DeliveryMethods.Post).
                forEach(payment -> payment.setDeliveryMethod(DeliveryMethods.Email));
    }
    @Override
    public void execute() {
        setDeliveryMethod();
    }
}
