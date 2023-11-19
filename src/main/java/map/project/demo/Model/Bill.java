package map.project.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "bill", fetch = FetchType.EAGER)
    List<Reading> readings;

    @ManyToOne
    @JoinColumn(name = "unitId")
    private Unit unit;

    @OneToMany(mappedBy = "bill" , fetch = FetchType.EAGER)
    private List<Payment> payments;

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", deliveryMethod=" + deliveryMethod +
                ", paymentStatus=" + paymentStatus +
                '}';


    }

    /**
     * stream to get the amount necessary to pay the bill
     *
     * @return Long sum
     */
    public Long getAllReadingsSum() {
        return this.readings.stream()
                .map(elem -> Pair.of(elem.getCounter().getPricePerUnit(), elem.getVolumeReading()))
                .toList().stream().map(value -> value.getFirst() * value.getSecond())
                .reduce(0L, Long::sum);
    }

    /**
     * observer pattern implementation
     * this is the subject (Bill) that notifies the Observers ( Payment) when the amount stacked into payments
     * is enough to pay the bill , the payments will receive the notification to change the bill status
     */
    //TODO add this method to whenever a payment is created
    public void notifyPayments() {
        Long sum = 0L;
        for (Payment payment : this.payments) {
            sum += payment.getAmount();
        }
        if(sum >= getAllReadingsSum()){
            this.payments.forEach(Payment::updateStatus);
        }
    }
}
