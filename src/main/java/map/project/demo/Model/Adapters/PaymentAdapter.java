package map.project.demo.Model.Adapters;

import map.project.demo.Model.Payment;
import map.project.demo.Model.dto.PaymentDto;

public class PaymentAdapter implements Adapter<Payment, PaymentDto> {
    @Override
    public PaymentDto transformToDto(Payment concreteObject) {
        return new PaymentDto(concreteObject.getBankStatmentId(),
                concreteObject.getBank(),
                concreteObject.getAmount(),
                concreteObject.getDeliveryMethods());
    }
}
