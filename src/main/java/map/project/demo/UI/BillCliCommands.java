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

@ShellComponent
public class BillCliCommands {
    @Autowired
    private BillService billService;
    @Autowired
    private UnitService unitService;

    @ShellMethod(key = "bills", value = "show all bills")
    public String allBills() {
        return billService.findAllBills().toString();
    }

    @ShellMethod(key = "by unit", value = "show all bills from a unit ")
    public String allBillsFromUnit(@ShellOption(value = {"unitId"}, help = "Id of the unit") Long unitId) {
        Optional<Unit> unit = unitService.findByUnitId(unitId);
        if (unit.equals(Optional.empty())) {
            return "There is no unit with this id";
        }
        return billService.findAllBillsByUnit(unit.get()).toString();
    }

    @ShellMethod(key = "add bill", value = "add a bill to our database")
    public String addBill(@ShellOption(value = {"delivery"}, help = "delivery method for the bill") DeliveryMethods deliveryMethod,
                          @ShellOption(value = {"unit"}, help = "the unit to which it belongs mapped by id") Long unitId,
                          @ShellOption(value = {"status"}, help = "payment status of the bill") PaymentStatus status) {
        Optional<Unit> unit = unitService.findByUnitId(unitId);
        if (unit.equals(Optional.empty())) {
            return "There is no unit with this id";
        }
        Bill addBill = new Bill();
        addBill.setUnit(unit.get());
        addBill.setDeliveryMethod(deliveryMethod);
        addBill.setPaymentStatus(status);
        return this.billService.save(addBill).toString();
    }

    @ShellMethod(key = "update bill", value = "update a bill from our database")
    public String updateBill(@ShellOption(value = {"billId"}, help = "id of the bill") Long billId,
                             @ShellOption(value = {"delivery"}, help = "delivery method for the bill") DeliveryMethods deliveryMethod,
                             @ShellOption(value = {"unit"}, help = "the unit to which it belongs mapped by id") Long unitId,
                             @ShellOption(value = {"status"}, help = "payment status of the bill") PaymentStatus status) {
        Optional<Unit> unit = unitService.findByUnitId(unitId);
        if (unit.equals(Optional.empty())) {
            return "There is no unit with this id";
        }
        Optional<Bill> bill = billService.findByBillId(billId);
        if (bill.equals(Optional.empty())) {
            return "There is no bill with this id";
        }
        Bill updateBill = bill.get();
        updateBill.setUnit(unit.get());
        updateBill.setDeliveryMethod(deliveryMethod);
        updateBill.setPaymentStatus(status);
        return this.billService.save(updateBill).toString();
    }

    @ShellMethod(key = "delete bill", value = "delete a bill from our database")
    public String deleteBill(@ShellOption(value = {"billId"}, help = "id of the bill") Long billId) {
        Optional<Bill> bill = billService.findByBillId(billId);
        if (bill.equals(Optional.empty())) {
            return "There is no bill with this id";
        }
        billService.delete(bill.get());
        return "Bill deleted";
    }

    @ShellMethod(key = "read bill", value = "read a bill from our database")
    public String readBill(@ShellOption(value = {"billId"}, help = "id of the bill") Long billId) {
        Optional<Bill> bill = billService.findByBillId(billId);
        if (bill.equals(Optional.empty())) {
            return "There is no bill with this id";
        }
        return bill.get().toString();
    }

}
