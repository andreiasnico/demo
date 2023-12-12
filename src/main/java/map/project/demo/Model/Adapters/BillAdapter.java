package map.project.demo.Model.Adapters;

import map.project.demo.Model.Bill;
import map.project.demo.Model.dto.BillDto;
import map.project.demo.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;

public class BillAdapter implements Adapter<Bill , BillDto> {

    @Override
    public BillDto transformToDto(Bill concreteObject) {
        BillDto billDto = new BillDto(concreteObject.getBankStatmentId(),
                concreteObject.getPaymentStatus(),
                concreteObject.getDeliveryMethods());
        return billDto;
    }

}
