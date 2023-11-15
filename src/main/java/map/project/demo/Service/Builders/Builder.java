package map.project.demo.Service.Builders;

import map.project.demo.Model.DeliveryMethods;
import map.project.demo.Model.PaymentStatus;
import map.project.demo.Service.BillService;

/**
 * class created for the builder design pattern in order to init the repos / services in a useful way for our data ,
 * in order to get
 */
public class Builder {
    private final BillService billService;

    public Builder(BillService billService) {
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

    public void execute() {
        this.setBillDelivery();
        this.setBillToUnpayed();
    }

}
