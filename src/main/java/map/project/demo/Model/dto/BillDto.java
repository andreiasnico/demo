package map.project.demo.Model.dto;

import map.project.demo.Model.DeliveryMethods;
import map.project.demo.Model.PaymentStatus;

public class BillDto {

    private Long billId;

    private PaymentStatus status;

    private DeliveryMethods deliveryMethod;

    public BillDto(Long billId, PaymentStatus status, DeliveryMethods deliveryMethod) {
        this.billId = billId;
        this.status = status;
        this.deliveryMethod = deliveryMethod;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public DeliveryMethods getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethods deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }
}
