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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * class for the building command line interface
 */
@ShellComponent
public class BuildingCliCommands {
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private UnitService unitService;

    /**
     * method that returns all buildings
     *
     * @return String of the list of buildings
     */
    @ShellMethod(key = "Building", value = "list all buildings")
    public String findAllBuildings() {
        return this.buildingService.findAll().toString();

    }

    /**
     * method where we add a building to our database
     *
     * @param address address of the building
     * @param stories number of stories
     * @param name    name of the building
     * @return returns the added buidling
     */
    @ShellMethod(key = "add building", value = "add a building to our database")
    public String addBuilding(@ShellOption(value = {"street"}, help = "address of the building") String address,
                              @ShellOption(value = {"town"} , help = "name of the town") String town,
                              @ShellOption(value = {"stories"}, help = "number of stories") int stories,
                              @ShellOption(value = {"name"}, help = "name of the building") String name) {
        Building addBuilding = new Building();
        addBuilding.setStreet(address);
        addBuilding.setTown(town);
        addBuilding.setName(name);
        addBuilding.setNumberOfStories(stories);
        return this.buildingService.save(addBuilding).toString();
    }

    /**
     * method where we update a building
     *
     * @param buildingId id of the building
     * @param stories    number of stories
     * @param name       name of the building
     * @return String of the updated bill
     * noted that we do not update Street , Town because it will not make sense
     */
    @ShellMethod(key = "update building", value = "update a building from our database")
    public String updateBuilding(@ShellOption(value = {"buildingId"}, help = "id of the building") Long buildingId,
                                 @ShellOption(value = {"stories"}, help = "number of stories") int stories,
                                 @ShellOption(value = {"name"}, help = "name of the building") String name
    ) {
        Optional<Building> updateBuilding = this.buildingService.findBuildingById(buildingId);
        if(updateBuilding.isEmpty()){
            return "There is no building with this id";
        }
        updateBuilding.get().setNumberOfStories(stories);
        updateBuilding.get().setName(name);
        this.buildingService.updateBuilding(updateBuilding.get());
        return "Building updated";
    }

    /**
     * Method that deletes a building from the database
     *
     * @param buildingId building id
     * @return message that bill has been deleted
     */
    @ShellMethod(key = "delete building", value = "delete a building from our database")
    public String deleteBuilding(@ShellOption(value = {"buildingId"}, help = "id of the building") Long buildingId) {
        Optional<Building> foundBuilding = this.buildingService.findBuildingById(buildingId);
        if(foundBuilding.isEmpty()){
            return "There is no building with this id";
        }
        this.buildingService.deleteBuilding(foundBuilding.get());
        return "Building deleted";
    }

    /**
     * method that returns information of the building
     *
     * @param buildingId building id
     * @return String of the building
     */
    @ShellMethod(key = "building info", value = "read a building from our database")
    public String buildingInfo(@ShellOption(value = {"buildingId"}, help = "id of the building") Long buildingId) {
        Optional<Building> foundBuilding = this.buildingService.findBuildingById(buildingId);
        if(foundBuilding.isEmpty()){
            return "There is no building with this id";
        }
        this.buildingService.readBuilding(foundBuilding.get());
        return "Building read";
    }

}
