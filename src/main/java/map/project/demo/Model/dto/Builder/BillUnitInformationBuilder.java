package map.project.demo.Model.dto.Builder;

import map.project.demo.Model.Bill;

public class BillUnitInformationBuilder implements Builder<Bill,BillUnitInformationDto> {
    @Override
    public BillUnitInformationDto buildObject(Bill object) {
        BillInformationBuilder billInformationBuilder = new BillInformationBuilder();
        BillInformationDto billInformationDto = billInformationBuilder.buildObject(object);

        return new BillUnitInformationDto(billInformationDto.getBillId(),
                billInformationDto.getPaymentStatus(),
                billInformationDto.getDeliveryMethods(),
                object.getUnit().getUnitId(),
                object.getUnit().getName());
    }
}
