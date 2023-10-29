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
    @ShellMethod(key = "bills" , value = "show all bills")
    public String allBills(){
        return billService.findAllBills().toString();
    }

    @ShellMethod(key="by unit" , value = "show all bills from a unit ")
    public String allBillsFromUnit(@ShellOption(value = {"unitId"} , help = "Id of the unit") Long unitId){
        Optional<Unit> unit = unitService.findByUnitId(unitId);
        if(unit .equals( Optional.empty())){
            return "There is no unit with this id";
        }
        return billService.findAllBillsByUnit(unit.get()).toString();
    }

    @ShellMethod(key="add bill" , value = "add a bill to our database")
    public String addBill(@ShellOption(value = {"delivery"} , help = "delivery method for the bill")DeliveryMethods deliveryMethod,
                          @ShellOption(value = {"unit"} , help = "the unit to which it belongs mapped by id") Long unitId ,
                          @ShellOption(value = {"status"} , help = "payment status of the bill")PaymentStatus status){
        Optional<Unit> unit = unitService.findByUnitId(unitId);
        if(unit.equals(Optional.empty())){
            return "There is no unit with this id";
        }
        Bill addBill = new Bill();
        addBill.setUnit(unit.get());
        addBill.setDeliveryMethod(deliveryMethod);
        addBill.setPaymentStatus(status);
        return this.billService.save(addBill).toString();
    }
}
