package map.project.demo.Model.dto.Builder;

import map.project.demo.Model.DeliveryMethods;
import map.project.demo.Model.PaymentStatus;

public class BillUnitInformationDto {

    private Long billId;

    private PaymentStatus paymentStatus;

    private DeliveryMethods deliveryMethod;

    private Long unitId;

    private String unitName;

    public BillUnitInformationDto(Long billId, PaymentStatus paymentStatus, DeliveryMethods deliveryMethod, Long unitId, String unitName) {
        this.billId = billId;
        this.paymentStatus = paymentStatus;
        this.deliveryMethod = deliveryMethod;
        this.unitId = unitId;
        this.unitName = unitName;
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
}
