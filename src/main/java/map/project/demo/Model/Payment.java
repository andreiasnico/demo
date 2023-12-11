package map.project.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name = "Payment")
public class Payment extends BankStatment{

    @Column(name = "bank")
    private String bank;

    @Column(name = "amount")
    private Long amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "billId")
    private Bill bill;

    public void updateStatus(){
        this.paymentStatus = PaymentStatus.Payed;

    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + bankStatmentId +
                ", bank='" + bank + '\'' +
                ", amount=" + amount +
                ", billStatus=" + paymentStatus +
                ", deliveryMethod=" + deliveryMethods +
                '}';
    }
}
