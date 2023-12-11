package map.project.demo.Service.Commanders;

import map.project.demo.Model.Bill;
import map.project.demo.Model.DeliveryMethods;
import map.project.demo.Model.PaymentStatus;
import map.project.demo.Service.BillService;

public  class BillCommander implements Commander{
    private final BillService billService;

    private static BillCommander instance = null;
    private Object bill;


    private BillCommander(BillService billService) {
        this.billService = billService;
    }

    public static BillCommander getInstance(BillService service){
        if(instance == null){
            instance = new BillCommander(service);
        }
        return instance;
    }
    /**
     * we want to go and set all the bills that have no payment status to Pending
     */
    private void setBillToUnpayed() {
//        this.billService.findAllBills().stream().filter(bill -> bill.getPaymentStatus() != PaymentStatus.Payed).
//                forEach(bill -> bill.setPaymentStatus(PaymentStatus.Pending));

        for (Bill b : this.billService.findAllBills()
        ) {
            if(b.getPaymentStatus() != PaymentStatus.Payed){
                b.setPaymentStatus(PaymentStatus.Pending);
            }
        }
    }

    /**
     * we want to set all the bills that have no delivery method to Email
     */
    private void setBillDelivery() {
        this.billService.findAllBills().stream().filter(bill -> bill.getDeliveryMethods() != DeliveryMethods.Post).
                forEach(bill -> bill.setDeliveryMethods(DeliveryMethods.Email));
    }
    @Override
    public void execute() {
        this.setBillDelivery();
        this.setBillToUnpayed();
    }


}
