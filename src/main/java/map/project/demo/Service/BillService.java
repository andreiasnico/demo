package map.project.demo.Service;

import map.project.demo.Model.Bill;
import map.project.demo.Model.Unit;
import map.project.demo.Repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;
    //todo implementation of harder stuff than the repo


    public Optional<Bill> findByBillId(Long billId) {
        return Optional.ofNullable(this.billRepository.findByBillId(billId));
    }

    public List<Bill> findAllBills() {
        return this.billRepository.findAll();
    }

    public List<Bill> findAllBillsByUnit(Unit unit) {
        return this.billRepository.findAllByUnit(unit);
    }

    public Bill save(Bill bill) {
        this.billRepository.save(bill);
        return bill;
    }

    public void delete(Bill bill) {
        this.billRepository.delete(bill);

    }
}
