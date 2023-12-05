package map.project.demo.UI;

import map.project.demo.Model.Building;
import map.project.demo.Model.Unit;
import map.project.demo.Service.BuildingService;
import map.project.demo.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;

@ShellComponent
public class BuildingCliCommands {
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private UnitService unitService;

    @ShellMethod(key = "Building", value = "list all buildings")
    public void findAllBuildings() {
        for (Building building : buildingService.findAll()) {
            System.out.println(building);
        }

    }

    @ShellMethod(key = "add building", value = "add a building to our database")
    public String addBuilding(@ShellOption(value = {"address"}, help = "address of the building") String address,
                              @ShellOption(value = {"unit"}, help = "the unit to which it belongs mapped by id") Long unitId) {
        Building addBuilding = new Building();
        addBuilding.setAddress(address);
        if(unitId != null) {
            Unit unit = unitService.findByUnitId(unitId).get();
            addBuilding.setUnit(unit);
        }
        return this.buildingService.save(addBuilding).toString();
    }

    @ShellMethod(key = "update building", value = "update a building from our database")
    public String updateBuilding(@ShellOption(value = {"buildingId"}, help = "id of the building") Long buildingId,
                                 @ShellOption(value = {"address"}, help = "address of the building") String address,
                                 @ShellOption(value = {"unit"}, help = "the unit to which it belongs mapped by id") Long unitId) {
        Building updateBuilding = new Building();
        updateBuilding.setBuildingId(buildingId);
        updateBuilding.setAddress(address);
        if(unitId != null) {
            Unit unit = unitService.findByUnitId(unitId).get();
            updateBuilding.setUnit(unit);
        }
        this.buildingService.updateBuilding(updateBuilding);
        return "Building updated";
    }

    @ShellMethod(key = "delete building", value = "delete a building from our database")
    public String deleteBuilding(@ShellOption(value = {"buildingId"}, help = "id of the building") Long buildingId) {
        Building deleteBuilding = new Building();
        deleteBuilding.setBuildingId(buildingId);
        this.buildingService.deleteBuilding(deleteBuilding);
        return "Building deleted";
    }

    @ShellMethod(key = "read building", value = "read a building from our database")
    public String readBuilding(@ShellOption(value = {"buildingId"}, help = "id of the building") Long buildingId) {
        Building readBuilding = new Building();
        readBuilding.setBuildingId(buildingId);
        this.buildingService.readBuilding(readBuilding);
        return "Building read";
    }

}
