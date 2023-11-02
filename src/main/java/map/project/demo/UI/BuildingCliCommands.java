//package map.project.demo.UI;
//
//import map.project.demo.Model.Building;
//import map.project.demo.Model.Unit;
//import map.project.demo.Repository.BuildingRepository;
//import map.project.demo.Repository.UnitRepository;
//import map.project.demo.Service.BuildingService;
//import map.project.demo.Service.UnitService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.shell.standard.ShellComponent;
//import org.springframework.shell.standard.ShellMethod;
//
//import java.util.Optional;
//
//@ShellComponent
//
//public class BuildingCliCommands {
//    @Autowired
//    private BuildingService buildingService;
//    @Autowired
//    private BuildingRepository buildingRepository;
//    @Autowired
//    private UnitService unitService;
//
//    @ShellMethod(key = "buildings", value = "show all buildings")
//    public String allBuildings() {
//        return buildingService.findAllBuildings().toString();
//    }
//
//    @ShellMethod(key = "by building", value = "show all buildings from a building ")
//    public String allBuildingsFromBuilding() {
//        return buildingService.findAllBuildings().toString();
//    }
//
//    @ShellMethod(key = "add building", value = "add a building to our database")
//    public String addBuilding() {
//        return buildingService.findAllBuildings().toString();
//    }
//
//    @ShellMethod(key = "create building", value = "create a building")
//    public String createBuilding() {
//        return buildingService.findAllBuildings().toString();
//    }
//
//    @ShellMethod(key = "update building", value = "update a building")
//    public String updateBuilding() {
//        return buildingService.findAllBuildings().toString();
//    }
//
//    @ShellMethod(key = "delete building", value = "delete a building")
//    public String deleteBuilding() {
//        return buildingService.findAllBuildings().toString();
//    }
//
//}
