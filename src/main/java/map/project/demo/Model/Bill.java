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
public class Bill extends BankStatment{


    @OneToMany(mappedBy = "bill", fetch = FetchType.EAGER)
    List<Reading> readings;

    @ManyToOne
    @JoinColumn(name = "unitId")
    private Unit unit;

    @OneToMany(mappedBy = "bill" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private List<Payment> payments;

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + bankStatmentId +
                ", deliveryMethod=" + deliveryMethods +
                ", paymentStatus=" + paymentStatus +
                '}';


    }

}
