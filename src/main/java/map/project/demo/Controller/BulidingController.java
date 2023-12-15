package map.project.demo.Controller;


import map.project.demo.Model.Adapters.AdapterFacade;
import map.project.demo.Model.Building;
import map.project.demo.Model.dto.BuildingDto;
import map.project.demo.Service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/building")
public class BulidingController {
    @Autowired
    private BuildingService buildingService;

    @GetMapping("/all-buildings")
    public List<BuildingDto> findAllBuildings() {
        List<Building> buildings = buildingService.findAll();

        return buildings.stream().map(building -> (BuildingDto) AdapterFacade.adaptToDto(building, Building.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/add-building")
    public BuildingDto addBuilding(@RequestParam String street,
                                   @RequestParam String town,
                                   @RequestParam int stories,
                                   @RequestParam String name){
        Building building = new Building();
        building.setStreet(street);
        building.setTown(town);
        building.setNumberOfStories(stories);
        building.setName(name);
        this.buildingService.save(building);
        return (BuildingDto) AdapterFacade.adaptToDto(building,Building.class);
    }

    @PostMapping("/update-building")
    public BuildingDto updateBuilding(@RequestParam Long buildingId,
                                      @RequestParam String name,
                                      @RequestParam int stories){
        Optional<Building> building = this.buildingService.findBuildingById(buildingId);
        if(building.isEmpty()){
            return null;
        }
        building.get().setName(name);
        building.get().setNumberOfStories(stories);
        this.buildingService.save(building.get());
        return (BuildingDto) AdapterFacade.adaptToDto(building.get(),Building.class);
    }

    @PostMapping("/delete-building")
    public BuildingDto deleteBuilding(@RequestParam Long buildingId){
        Optional<Building> building = this.buildingService.findBuildingById(buildingId);
        if(building.isEmpty()){
            return null;
        }
        this.buildingService.deleteBuilding(building.get());
        return (BuildingDto) AdapterFacade.adaptToDto(building.get(),Building.class);
    }

}
