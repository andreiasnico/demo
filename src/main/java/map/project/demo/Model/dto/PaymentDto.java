package map.project.demo.Model.dto;

import map.project.demo.Model.DeliveryMethods;

public class PaymentDto {

    private Long paymentId;

    private String bank;;

    private Long amount;

    private DeliveryMethods deliveryMethod;

    public PaymentDto(Long paymentId, String bank, Long amount, DeliveryMethods deliveryMethod) {
        this.paymentId = paymentId;
        this.bank = bank;
        this.amount = amount;
        this.deliveryMethod = deliveryMethod;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public DeliveryMethods getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethods deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }
}
