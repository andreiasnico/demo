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
public class Payment {
    @Id
    @GeneratedValue
    private Long paymentId;

    @Column(name = "bank")
    private String bank;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "billStatus")
    private BillStatus billStatus ;

    @ManyToOne
    @JoinColumn(name = "billId")
    private Bill bill;

    public void updateStatus(){
        this.billStatus = BillStatus.Payed;
    }
}
