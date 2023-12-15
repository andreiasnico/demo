package map.project.demo.Controller;

import map.project.demo.Model.Adapters.AdapterFacade;
import map.project.demo.Model.Bill;
import map.project.demo.Model.DeliveryMethods;
import map.project.demo.Model.PaymentStatus;
import map.project.demo.Model.Unit;
import map.project.demo.Model.dto.BillDto;
import map.project.demo.Service.BillService;
import map.project.demo.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService service;

    @Autowired
    private UnitService unitService;

    /**
     * find all method
     *
     * @return List of billDto objects
     */
    @GetMapping("/find-all-bills")
    public List<BillDto> findAllBills() {
        List<Bill> bills = service.findAllBills();
        return bills.stream().map(bill -> (BillDto) AdapterFacade.adaptToDto(bill, Bill.class))
                .collect(Collectors.toList());
    }

    /**
     * add bill api
     * @param deliveryMethod Delivery method
     * @param unitId unitId
     * @param status Payment Status
     * @return
     */
    @PostMapping("/add-bill")
    public BillDto addBill(@RequestParam String deliveryMethod,
                           @RequestParam Long unitId,
                           @RequestParam String status) {
        Bill bill = new Bill();
        bill.setDeliveryMethods(DeliveryMethods.valueOf(deliveryMethod));
        bill.setPaymentStatus(PaymentStatus.valueOf(status));
        Optional<Unit> unit = this.unitService.findByUnitId(unitId);
        if(unit.isEmpty()){
            return null;
        }
        bill.setUnit(unit.get());
        this.service.save(bill);
        return (BillDto) AdapterFacade.adaptToDto(bill , Bill.class);
    }

    @PostMapping("/delete-bill")
    public BillDto deleteBill(@RequestParam Long billId){
        Optional<Bill> bill = this.service.findByBillId(billId);
        if(bill.isEmpty()){
            return null;
        }
        this.service.delete(bill.get());
        return (BillDto) AdapterFacade.adaptToDto(bill.get() , Bill.class);
    }

    @PostMapping("/update-bill")
    public BillDto updateBill(@RequestParam Long billId,
                              @RequestParam String delivery,
                              @RequestParam String status){
        Optional<Bill> bill = this.service.findByBillId(billId);
        if(bill.isEmpty()){
            return null;
        }
        bill.get().setDeliveryMethods(DeliveryMethods.valueOf(delivery));
        bill.get().setPaymentStatus(PaymentStatus.valueOf(status));
        this.service.save(bill.get());
        return (BillDto) AdapterFacade.adaptToDto(bill.get(),Bill.class);
    }
}
