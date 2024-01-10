package map.project.demo.Model.dto.Builder;

import map.project.demo.Model.DeliveryMethods;
import map.project.demo.Model.PaymentStatus;

public class BillRenterInformationDto {

    private Long billId;

    private PaymentStatus paymentStatus;

    private DeliveryMethods deliveryMethod;

    private Long amount;

    private Long unitId;

    private String unitName;

    private Long renterId;

    private String renterName;

    public BillRenterInformationDto(Long billId, PaymentStatus paymentStatus, DeliveryMethods deliveryMethod,
                                    Long amount, Long unitId, String unitName, Long renterId, String renterName) {
        this.billId = billId;
        this.paymentStatus = paymentStatus;
        this.deliveryMethod = deliveryMethod;
        this.amount = amount;
        this.unitId = unitId;
        this.unitName = unitName;
        this.renterId = renterId;
        this.renterName = renterName;
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

    public DeliveryMethods getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethods deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Long getRenterId() {
        return renterId;
    }

    public void setRenterId(Long renterId) {
        this.renterId = renterId;
    }

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }
}
