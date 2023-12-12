package map.project.demo.Controller;


import map.project.demo.Model.Adapters.AdapterFacade;
import map.project.demo.Model.Building;
import map.project.demo.Model.dto.BuildingDto;
import map.project.demo.Service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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


}
