package map.project.demo.Service;
import map.project.demo.Model.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import map.project.demo.Repository.BuildingRepository;

import java.util.Optional;

@Service
public class BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    public Optional<Building> findByBuildingId(Long buildingId) {
        return buildingRepository.findByBuildingId(buildingId);
    }

    public Building save(Building building) {
        buildingRepository.save(building);
        return building;
    }

    public Iterable<Building> findAll() {
        return buildingRepository.findAll();
    }

    public void delete(Building building) {
        buildingRepository.delete(building);
    }



}
