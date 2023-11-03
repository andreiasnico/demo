package map.project.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@Table(name = "Bill")
public class Bill {
    @Id
    @GeneratedValue
    private Long billId;

    @Column(name = "deliveryMethod")
    private DeliveryMethods deliveryMethod;

    @Column(name = "paymentStatus")
    private PaymentStatus paymentStatus;

    @OneToMany(mappedBy = "bill", fetch = FetchType.LAZY)
    List<Reading> readings;

    @ManyToOne
    @JoinColumn(name = "unitId")
    private Unit unit;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Payment payment;

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", deliveryMethod=" + deliveryMethod +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
