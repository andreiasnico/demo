package map.project.demo.Model.dto.Builder;

import map.project.demo.Model.Bill;

public class BillRenterInformationBuilder implements Builder<Bill , BillRenterInformationDto> {

    @Override
    public BillRenterInformationDto buildObject(Bill object) {
        BillUnitInformationBuilder billUnitInformationBuilder = new BillUnitInformationBuilder();
        BillUnitInformationDto billUnitInformationDto = billUnitInformationBuilder.buildObject(object);

        return new BillRenterInformationDto(billUnitInformationDto.getBillId(),
                billUnitInformationDto.getPaymentStatus(),
                billUnitInformationDto.getDeliveryMethod(),
                billUnitInformationDto.getTotalAmount(),
                billUnitInformationDto.getUnitId(),
                billUnitInformationDto.getUnitName(),
                object.getUnit().getRenter().getRenterId(),
                object.getUnit().getRenter().getFirmName());
    }
}
