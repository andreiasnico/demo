package map.project.demo.Controller;

import map.project.demo.Model.Bill;
import map.project.demo.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BillController {
    @Autowired
    private BillService service;


    @GetMapping("/bills")
    public List<Bill> findAllBills(){
        return service.findAllBills();
    }

}
