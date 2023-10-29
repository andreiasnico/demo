package map.project.demo.Service;

import map.project.demo.Model.Bill;
import map.project.demo.Repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    private BillRepository billRepository;
    //todo implementation of harder stuff than the repo


    public Optional<Bill> findByBillId(Long billId){
        return Optional.ofNullable(billRepository.findByBillId(billId));
    }

    public List<Bill> findAllBills(){
        return billRepository.findAll();
    }
}
