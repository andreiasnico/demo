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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public String getAddress() {
        return street + ", " + town;
    }

    public void setAddress(String address) {
        String[] parts = address.split(", ");
        this.street = parts[0];
        this.town = parts[1];
    }

    public void setUnit(Unit unit) {
        this.units.add(unit);
    }
}
