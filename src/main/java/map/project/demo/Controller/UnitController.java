package map.project.demo.Controller;

import map.project.demo.Model.Bill;
import map.project.demo.Model.Building;
import map.project.demo.Model.Renter;
import map.project.demo.Model.Unit;
import map.project.demo.Service.BuildingService;
import map.project.demo.Service.RenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import map.project.demo.Service.UnitService;

import java.util.Optional;

@RestController
@RequestMapping("/unit")
public class UnitController {
    @Autowired
    private UnitService service;

    @Autowired
    private RenterService renterService;

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/units")
    public String findAllUnits() {
        return service.findAllUnits().toString();
    }

    @PostMapping("/add-unit")
    public String addUnit(@RequestParam String name,
                          @RequestParam int storyNumber,
                          @RequestParam Long surface,
                          @RequestParam Long buildingId,
                          @RequestParam Long renterId){
        Optional<Renter> renter = this.renterService.findBYRenterId(renterId);

        if(renter.isEmpty()){
            return null;
        }

        Optional<Building> building = this.buildingService.findBuildingById(buildingId);

        if(building.isEmpty()){
            return null;
        }

        Unit unit = new Unit();
        unit.setRenter(renter.get());
        unit.setName(name);
        unit.setSurface(surface);
        unit.setStoryNumber(storyNumber);
        unit.setBuilding(building.get());
        this.service.addUnit(unit);
        return unit.toString();
    }

    @PostMapping("/delete-unit")
    public String deleteUnit(@RequestParam Long unitId){
        Optional<Unit> unit = this.service.findByUnitId(unitId);
        if(unit.isEmpty()){
            return null;
        }

        this.service.delete(unit.get());
        return unit.get().toString();
    }

    @PostMapping("/update-unit")
    public String updateUnit(@RequestParam Long unitId,
                             @RequestParam String name,
                             @RequestParam int storyNumber,
                             @RequestParam Long surface,
                             @RequestParam Long buildingId,
                             @RequestParam Long renterId){
        Optional<Renter> renter = this.renterService.findBYRenterId(renterId);

        if(renter.isEmpty()){
            return null;
        }

        Optional<Building> building = this.buildingService.findBuildingById(buildingId);

        if(building.isEmpty()){
            return null;
        }

        Optional<Unit> unit = this.service.findByUnitId(unitId);
        if(unit.isEmpty()){
            return null;
        }

        unit.get().setBuilding(building.get());
        unit.get().setRenter(renter.get());
        unit.get().setName(name);
        unit.get().setSurface(surface);
        unit.get().setStoryNumber(storyNumber);
        this.service.addUnit(unit.get());
        return unit.get().toString();
    }
}
