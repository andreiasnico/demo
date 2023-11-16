//package map.project.demo.UI;
//
//import map.project.demo.Model.Building;
//import map.project.demo.Model.Unit;
//import map.project.demo.Service.BuildingService;
//import map.project.demo.Service.UnitService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.shell.standard.ShellComponent;
//import org.springframework.shell.standard.ShellMethod;
//
//@ShellComponent
//public class BuildingCliCommands {
//    @Autowired
//    private BuildingService buildingService;
//
//
//    @ShellMethod(key = "Building", value = "show all buildings")
//    public String allBuildings() {
//        return buildingService.findAll().toString();
//    }
//
//}
