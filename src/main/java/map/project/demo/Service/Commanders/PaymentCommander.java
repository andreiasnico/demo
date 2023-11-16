package map.project.demo.Service.Commanders;

import map.project.demo.Model.DeliveryMethods;
import map.project.demo.Model.Payment;
import map.project.demo.Service.PaymentService;

public class PaymentCommander implements Commander {

    private PaymentService paymentService ;

    private static PaymentCommander instance = null;

    private PaymentCommander(PaymentService service){
        this.paymentService = service;
    }

    public static PaymentCommander getInstance(PaymentService service){
        if(instance == null){
            instance = new PaymentCommander(service);
        }
        return instance;
    }

    private void setDeliveryMethod(){
        this.paymentService.findAllPayments().stream().filter(payment -> payment.getDeliveryMethod() != DeliveryMethods.Post).
                forEach(payment -> payment.setDeliveryMethod(DeliveryMethods.Email));
    }
    @Override
    public void execute() {
        setDeliveryMethod();
    }
}
