package map.project.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@Table(name = "Counter")
public class Counter {

    @Id
    @GeneratedValue
    private Long counterId;

    @Column(name = "counterTypes")
    private CounterTypes counterTypes;

    @Column(name = "pricePerUnit")
    private long pricePerUnit;

    @Column(name = "checkingDate")
    private Date checkingDate;

    @OneToMany(mappedBy = "counter")
    List<Reading> readings;


}
