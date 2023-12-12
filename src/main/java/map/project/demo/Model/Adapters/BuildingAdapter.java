package map.project.demo.Model.Adapters;

import map.project.demo.Model.Building;
import map.project.demo.Model.dto.BuildingDto;
import map.project.demo.Service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;

public class BuildingAdapter implements Adapter<Building,BuildingDto> {
    @Override
    public BuildingDto transformToDto(Building concreteObject) {
        BuildingDto buildingDto = new BuildingDto(concreteObject.getBuildingId(),
                concreteObject.getName(),
                concreteObject.getStreet(),
                concreteObject.getTown(),
                concreteObject.getNumberOfStories()
                );
        return buildingDto;
    }

}
