package map.project.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Setter
@Getter
@Table(name = "Reading")
public class Reading {

    @Id
    @GeneratedValue
    private Long readingId;

    @Column(name = "volumeReading")
    private Long volumeReading;

    @ManyToOne
    @JoinColumn(name = "counterId")
    private Counter counter;

    @ManyToOne
    @JoinColumn(name = "billId")
    private Bill bill;
}
