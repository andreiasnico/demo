package map.project.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * superclass for the Bill and Payment classes
 */
@Entity
@Inheritance(strategy =  InheritanceType.TABLE_PER_CLASS)
public  class BankStatment {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long bankStatmentId;


    @Column(name = "deliveryMethod")
    protected DeliveryMethods deliveryMethods;

    @Column(name = "paymentStatus")
    protected PaymentStatus paymentStatus;

    public Long getBankStatmentId() {
        return bankStatmentId;
    }

    public void setBankStatmentId(Long bankStatmentId) {
        this.bankStatmentId = bankStatmentId;
    }

    public DeliveryMethods getDeliveryMethods() {
        return deliveryMethods;
    }

    public void setDeliveryMethods(DeliveryMethods deliveryMethods) {
        this.deliveryMethods = deliveryMethods;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


}
