package map.project.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@Table(name = "Building")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long buildingId;

    @Column(name = "name")
    private String name;

    @Column(name = "street")
    private String street;

    @Column(name = "town")
    private String town;

    @Column(name = "numberOfStories")
    private int numberOfStories;

    @OneToMany(mappedBy = "building", fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    List<Unit> units;

    public void setUnit(Unit unit) {
        this.units.add(unit);
    }
}
