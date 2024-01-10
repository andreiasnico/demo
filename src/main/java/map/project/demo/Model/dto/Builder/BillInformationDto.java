package map.project.demo.Model.dto.Builder;

import map.project.demo.Model.DeliveryMethods;
import map.project.demo.Model.PaymentStatus;

public class BillInformationDto {

    private Long billId;

    private PaymentStatus paymentStatus;

    private DeliveryMethods deliveryMethods;

    private Long amount;

    public BillInformationDto(Long billId, PaymentStatus paymentStatus, DeliveryMethods deliveryMethods, Long amount) {
        this.billId = billId;
        this.paymentStatus = paymentStatus;
        this.deliveryMethods = deliveryMethods;
        this.amount = amount;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public DeliveryMethods getDeliveryMethods() {
        return deliveryMethods;
    }

    public void setDeliveryMethods(DeliveryMethods deliveryMethods) {
        this.deliveryMethods = deliveryMethods;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BillInformationDto{" +
                "billId=" + billId +
                ", paymentStatus=" + paymentStatus +
                ", deliveryMethods=" + deliveryMethods +
                ", amount=" + amount +
                '}';
    }
}
