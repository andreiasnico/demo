package map.project.demo.Model.dto.Builder;

import map.project.demo.Model.Bill;

public class BillInformationBuilder implements Builder<Bill , BillInformationDto> {
    @Override
    public BillInformationDto buildObject(Bill object) {
        return new BillInformationDto(object.getBankStatmentId(),
                object.getPaymentStatus(),
                object.getDeliveryMethods());
    }
}
