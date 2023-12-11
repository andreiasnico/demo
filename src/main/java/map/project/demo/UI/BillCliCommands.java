package map.project.demo.UI;

import map.project.demo.Model.Bill;
import map.project.demo.Model.DeliveryMethods;
import map.project.demo.Model.PaymentStatus;
import map.project.demo.Model.Unit;
import map.project.demo.Service.BillService;
import map.project.demo.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.Optional;

/**
 * class for the command line interface for the bill class
 */
@ShellComponent
public class BillCliCommands {
    @Autowired
    private BillService billService;
    @Autowired
    private UnitService unitService;

    /**
     * method that prints out a list of all the bills our database
     *
     * @return String of the list of bills
     */
    @ShellMethod(key = "bills", value = "show all bills")
    public String allBills() {
        return billService.findAllBills().toString();
    }

    /**
     * method that prints out the bills from a single unit
     *
     * @param unitId the id of the unit
     * @return String of the list of the biils
     */
    @ShellMethod(key = "by unit", value = "show all bills from a unit ")
    public String allBillsFromUnit(@ShellOption(value = {"unitId"}, help = "Id of the unit") Long unitId) {
        Optional<Unit> unit = unitService.findByUnitId(unitId);
        if (unit.equals(Optional.empty())) {
            return "There is no unit with this id";
        }
        return billService.findAllBillsByUnit(unit.get()).toString();
    }

    /**
     * method that adds a bill to the database
     *
     * @param deliveryMethod delivery method of the bill
     * @param unitId         id of the unit which the bill goes to
     * @param status         status of the payment of the bill
     * @return String of the new bill added
     */
    @ShellMethod(key = "add bill", value = "add a bill to our database")
    public String addBill(@ShellOption(value = {"delivery"}, help = "delivery method for the bill") String deliveryMethod,
                          @ShellOption(value = {"unit"}, help = "the unit to which it belongs mapped by id") Long unitId,
                          @ShellOption(value = {"status"}, help = "payment status of the bill") String status) {
        Optional<Unit> unit = unitService.findByUnitId(unitId);
        if (unit.equals(Optional.empty())) {
            return "There is no unit with this id";
        }
        PaymentStatus paymentStatus = PaymentStatus.valueOf(status);
        DeliveryMethods delivery = DeliveryMethods.valueOf(DeliveryMethods.class, deliveryMethod);
        Bill addBill = new Bill();
        if (unitId != null) {
            addBill.setUnit(unit.get());
        }
        if (!deliveryMethod.equals("")) {
            addBill.setDeliveryMethods(delivery);
        }
        if (!status.equals("")) {
            addBill.setPaymentStatus(paymentStatus);
        }
        return this.billService.save(addBill).toString();
    }

    /**
     * method that updates the bill
     *
     * @param billId         id of the bill which we want to update
     * @param deliveryMethod delivery method of the bill
     * @param unitId         id of the unit which the bill goes to
     * @param status         status of the payment of the bill
     * @return String of the updated bill
     */
    @ShellMethod(key = "update bill", value = "update a bill from our database")
    public String updateBill(@ShellOption(value = {"billId"}, help = "id of the bill") Long billId,
                             @ShellOption(value = {"delivery"}, help = "delivery method for the bill") String deliveryMethod,
                             @ShellOption(value = {"unit"}, help = "the unit to which it belongs mapped by id") Long unitId,
                             @ShellOption(value = {"status"}, help = "payment status of the bill") String status) {
        Optional<Unit> unit = unitService.findByUnitId(unitId);
        if (unit.equals(Optional.empty())) {
            return "There is no unit with this id";
        }
        Optional<Bill> bill = billService.findByBillId(billId);
        if (bill.equals(Optional.empty())) {
            return "There is no bill with this id";
        }
        DeliveryMethods delivery = DeliveryMethods.valueOf(deliveryMethod);
        PaymentStatus payment = PaymentStatus.valueOf(status);
        Bill updateBill = bill.get();
        updateBill.setUnit(unit.get());
        updateBill.setDeliveryMethods(delivery);
        updateBill.setPaymentStatus(payment);
        return this.billService.save(updateBill).toString();
    }

    /**
     * method that deletes a bill from the database
     *
     * @param billId id of the bill we want deleted
     * @return String of whether the bill was deleted or not
     */
    @ShellMethod(key = "delete bill", value = "delete a bill from our database")
    public String deleteBill(@ShellOption(value = {"billId"}, help = "id of the bill") Long billId) {
        Optional<Bill> bill = billService.findByBillId(billId);
        if (bill.isEmpty()) {
            return "There is no bill with this id";
        }
        billService.delete(bill.get());
        return "Bill deleted";
    }

    /**
     * method that returns a bill based on id
     *
     * @param billId
     * @return String of the bill we want to see
     */
    @ShellMethod(key = "read bill", value = "read a bill from our database")
    public String readBill(@ShellOption(value = {"billId"}, help = "id of the bill") Long billId) {
        Optional<Bill> bill = billService.findByBillId(billId);
        if (bill.isEmpty()) {
            return "There is no bill with this id";
        }
        return bill.get().toString();
    }

}
