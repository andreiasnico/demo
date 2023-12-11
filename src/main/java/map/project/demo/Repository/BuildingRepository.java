package map.project.demo.Repository;

import map.project.demo.Model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<Building , Long> {
    Optional<Building> findByBuildingId(Long buildingId);

}
