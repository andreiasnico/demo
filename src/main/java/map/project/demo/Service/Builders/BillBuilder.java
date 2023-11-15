package map.project.demo.Service.Builders;

import map.project.demo.Model.DeliveryMethods;
import map.project.demo.Model.PaymentStatus;
import map.project.demo.Service.BillService;

public  class BillBuilder implements Builder{
    private final BillService billService;

    public BillBuilder(BillService billService) {
        this.billService = billService;
    }

    /**
     * we want to go and set all the bills that have no payment status to Pending
     */
    private void setBillToUnpayed() {
        this.billService.findAllBills().stream().filter(bill -> bill.getPaymentStatus() != PaymentStatus.Payed).
                forEach(bill -> bill.setPaymentStatus(PaymentStatus.Pending));
    }

    /**
     * we want to set all the bills that have no delivery method to Email
     */
    private void setBillDelivery() {
        this.billService.findAllBills().stream().filter(bill -> bill.getDeliveryMethod() != DeliveryMethods.Post).
                forEach(bill -> bill.setDeliveryMethod(DeliveryMethods.Email));
    }
    @Override
    public void execute() {
        this.setBillDelivery();
        this.setBillToUnpayed();
    }
}
