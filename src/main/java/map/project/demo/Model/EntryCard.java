package map.project.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "EntryCard")
@Getter
@Setter
public class EntryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empoyeeId;

    @Column
    private int securityLevel;

    @OneToOne(mappedBy = "card")
    private Employee employee;
}
