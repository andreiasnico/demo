package map.project.demo.Controller;

import map.project.demo.Model.Adapters.AdapterFacade;
import map.project.demo.Model.Bill;
import map.project.demo.Model.dto.BillDto;
import map.project.demo.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService service;

    @GetMapping("/find-all-bills")
    public List<BillDto> findAllBills() {
        List<Bill> bills = service.findAllBills();
        return bills.stream().map(bill -> (BillDto) AdapterFacade.adaptToDto(bill, Bill.class))
                .collect(Collectors.toList());
    }

}
