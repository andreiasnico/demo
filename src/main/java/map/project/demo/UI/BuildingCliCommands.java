package map.project.demo.UI;

import map.project.demo.Model.Building;
import map.project.demo.Model.Unit;
import map.project.demo.Service.BuildingService;
import map.project.demo.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
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



}
