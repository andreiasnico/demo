package map.project.demo.Model.Adapters;

import map.project.demo.Model.Renter;
import map.project.demo.Model.dto.RenterDto;

public class RenterAdapter implements Adapter<Renter, RenterDto> {
    @Override
    public RenterDto transformToDto(Renter concreteObject) {
        return new RenterDto(concreteObject.getRenterId(),
                concreteObject.getFirmName(),
                concreteObject.getEmail(),
                concreteObject.getIBAN());
    }
}
