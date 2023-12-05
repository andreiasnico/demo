package map.project.demo.UI;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import map.project.demo.Model.Unit;
import map.project.demo.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@ShellComponent
public class UnitCliCommands {
    @Autowired
    private UnitService unitService;

    @ShellMethod(key = "units", value = "show all units")
    public String allUnits() {
        return unitService.findAll().toString();
    }

    @ShellMethod(key = "add unit", value = "add a unit to our database")
    public String addUnit(@ShellOption(value = {"name"}, help = "name of the unit") String name,
                          @ShellOption(value = {"storyNumber"}, help = "number of stories") int storyNumber,
                          @ShellOption(value = {"surface"}, help = "surface of the unit") Long surface) {
        Unit addUnit = new Unit();
        addUnit.setName(name);
        addUnit.setStoryNumber(storyNumber);
        addUnit.setSurface(surface);
        return this.unitService.save(addUnit).toString();
    }

    @ShellMethod(key = "update unit", value = "update a unit from our database")
    public String updateUnit(@ShellOption(value = {"unitId"}, help = "id of the unit") Long unitId,
                             @ShellOption(value = {"name"}, help = "name of the unit") String name,
                             @ShellOption(value = {"storyNumber"}, help = "number of stories") int storyNumber,
                             @ShellOption(value = {"surface"}, help = "surface of the unit") Long surface) {
        Optional<Unit> unit = unitService.findByUnitId(unitId);
        if (unit.equals(Optional.empty())) {
            return "There is no unit with this id";
        }
        Unit updateUnit = unit.get();
        updateUnit.setName(name);
        updateUnit.setStoryNumber(storyNumber);
        updateUnit.setSurface(surface);
        return this.unitService.save(updateUnit).toString();
    }

    @ShellMethod(key = "delete unit", value = "delete a unit from our database")

    public String deleteUnit(@ShellOption(value = {"unitId"}, help = "id of the unit") Long unitId) {
        Optional<Unit> unit = unitService.findByUnitId(unitId);
        if (unit.equals(Optional.empty())) {
            return "There is no unit with this id";
        }
        this.unitService.delete(unit.get());
        return "Unit deleted";
    }

    @ShellMethod(key = "read unit", value = "read a unit from our database")
    public String readUnit(@ShellOption(value = {"unitId"}, help = "id of the unit") Long unitId) {
        Optional<Unit> unit = unitService.findByUnitId(unitId);
        if (unit.equals(Optional.empty())) {
            return "There is no unit with this id";
        }
        this.unitService.readUnit(unit.get());
        return "Unit read";
    }


}
