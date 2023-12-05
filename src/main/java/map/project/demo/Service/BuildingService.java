package map.project.demo.Service;
import map.project.demo.Model.Building;
import map.project.demo.Model.Unit;
import map.project.demo.Repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    public Optional<Building> findByUnitId(Long buildingId) {
        return buildingRepository.findById(buildingId);
    }

    public Building save(Building building) {
        buildingRepository.save(building);
        return building;
    }

    public List<Building> findAll() {
        return buildingRepository.findAll();

    }

    public void deleteBuilding(Building building) {
        buildingRepository.delete(building);
    }

    public void addBuilding(Building building) {
        buildingRepository.save(building);
    }

    public void updateBuilding(Building building) {
        Building updatedBuilding = buildingRepository.findById(building.getBuildingId()).get();
        updatedBuilding.setStreet(building.getAddress());
        updatedBuilding.setUnits(building.getUnits());
        buildingRepository.save(updatedBuilding);
    }

    public void readBuilding(Building building) {
        buildingRepository.findById(building.getBuildingId());
    }







}
