//package map.project.demo.UI;
//
//import map.project.demo.Model.Unit;
//import map.project.demo.Repository.UnitRepository;
//import map.project.demo.Service.UnitService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.shell.standard.ShellComponent;
//import org.springframework.shell.standard.ShellMethod;
//import org.springframework.stereotype.Component;
//
//
//
//
//@ShellComponent
//public class UnitCliCommands {
//
//   @Autowired
//
//    private  UnitService UnitService;
//    private  UnitRepository UnitRepository;
//
//    @ShellMethod(key = "units" , value = "show all units")
//    public String allUnits(){
//        return UnitService.findAllUnits().toString();
//    }
//    @ShellMethod(key = "by unit" , value = "show all units from a unit ")
//    public String allUnitsFromUnit(){
//        return UnitService.findAllUnits().toString();
//    }
//    @ShellMethod(key = "add unit" , value = "add a unit to our database")
//    public String addUnit(){
//        return UnitService.findAllUnits().toString();
//    }
//    @ShellMethod(key = "create unit" , value = "create a unit")
//    public String createUnit(){
//        return UnitService.findAllUnits().toString();
//    }
//    @ShellMethod(key = "update unit" , value = "update a unit")
//    public String updateUnit(){
//        return UnitService.findAllUnits().toString();
//    }
//    @ShellMethod(key = "delete unit" , value = "delete a unit")
//    public String deleteUnit(){
//        return UnitService.findAllUnits().toString();
//    }
//
//
//
//
//
//
//
//
//}
//
//
//
//
//
