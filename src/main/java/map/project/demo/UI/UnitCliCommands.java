package map.project.demo.UI;

import map.project.demo.Model.Building;
import map.project.demo.Model.Renter;
import map.project.demo.Service.BuildingService;
import map.project.demo.Service.RenterService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import map.project.demo.Model.Unit;
import map.project.demo.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * class that creates the command line interface for the unit class
 */
@ShellComponent
public class UnitCliCommands {
    @Autowired
    private UnitService unitService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private RenterService renterService;

    /**
     * method that returns all the units
     *
     * @return String of the list of units
     */
    @ShellMethod(key = "units", value = "show all units")
    public String allUnits() {
        return unitService.findAll().toString();
    }

    /**
     * method that adds a unit to the database
     *
     * @param name        name of the unit
     * @param storyNumber story number
     * @param surface     surface of the unit
     * @param buildingId  building
     * @param renterId    renter
     * @return String of the added unit
     */
    @ShellMethod(key = "add unit", value = "add a unit to our database")
    public String addUnit(@ShellOption(value = {"name"}, help = "name of the unit") String name,
                          @ShellOption(value = {"storyNumber"}, help = "number of stories") int storyNumber,
                          @ShellOption(value = {"surface"}, help = "surface of the unit") Long surface,
                          @ShellOption(value = {"buildingId"}, help = "id of the building") Long buildingId,
                          @ShellOption(value = {"renterId"}, help = "id of the renter") Long renterId
    ) {
        Optional<Building>  building = this.buildingService.findBuildingById(buildingId);
        if(building.isEmpty()){
            return "There is no building with this id";
        }
        Optional<Renter> renter = this.renterService.findBYRenterId(renterId);
        if(renter.isEmpty()){
            return "There is no renter with this id";
        }
        Unit addUnit = new Unit();
        addUnit.setName(name);
        addUnit.setStoryNumber(storyNumber);
        addUnit.setSurface(surface);
        addUnit.setBuilding(building.get());
        addUnit.setRenter(renter.get());
        return this.unitService.save(addUnit).toString();
    }

    /**
     * method that updates a unit based on id
     *
     * @param unitId   unit id
     * @param name     name of the unit
     * @param renterId renter
     * @return String of the updated unit
     */
    @ShellMethod(key = "update unit", value = "update a unit from our database")
    public String updateUnit(@ShellOption(value = {"unitId"}, help = "id of the unit") Long unitId,
                             @ShellOption(value = {"name"}, help = "name of the unit") String name,
                             @ShellOption(value = {"renterId"}, help = "id of the renter") Long renterId) {
        Optional<Unit> unit = unitService.findByUnitId(unitId);
        if (unit.equals(Optional.empty())) {
            return "There is no unit with this id";
        }
        Optional<Renter> renter = this.renterService.findBYRenterId(renterId);
        if(renter.isEmpty()){
            return "There is no renter with this id";
        }
        Unit updateUnit = unit.get();
        updateUnit.setName(name);
        updateUnit.setRenter(renter.get());
        return this.unitService.save(updateUnit).toString();
    }

    /**
     * method that deletes a unit
     *
     * @param unitId unit id
     * @return Promp that the unit has been deleted
     */
    @ShellMethod(key = "delete unit", value = "delete a unit from our database")
    public String deleteUnit(@ShellOption(value = {"unitId"}, help = "id of the unit") Long unitId) {
        Optional<Unit> unit = unitService.findByUnitId(unitId);
        if (unit.equals(Optional.empty())) {
            return "There is no unit with this id";
        }
        this.unitService.delete(unit.get());
        return "Unit deleted";
    }

    /**
     * method that gives the info of the unit
     *
     * @param unitId unit id
     * @return String of the unit info
     */
    @ShellMethod(key = "unit info", value = "read a unit from our database")
    public String readUnit(@ShellOption(value = {"unitId"}, help = "id of the unit") Long unitId) {
        Optional<Unit> unit = unitService.findByUnitId(unitId);
        if (unit.equals(Optional.empty())) {
            return "There is no unit with this id";
        }
        return unit.get().toString();
    }


}
