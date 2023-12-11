package map.project.demo.Service;

import map.project.demo.Model.Bill;
import map.project.demo.Model.Unit;
import map.project.demo.Repository.BillRepository;
import map.project.demo.Repository.UnitRepository;
import map.project.demo.Service.Commanders.BillCommander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UnitRepository unitRepository;
    public Optional<Bill> findByBillId(Long billId) {
        return Optional.ofNullable(this.billRepository.findByBankStatmentId(billId));
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

    // crud operations

    public void addBill(Bill bill) {
        this.billRepository.save(bill);
        BillCommander billCommander = BillCommander.getInstance(this); // maybe this is a little overkill
        billCommander.execute();
    }

    public void update(Bill bill) {
        this.billRepository.save(bill);
    }

    public void read(Bill bill) {
        this.billRepository.findById(bill.getBankStatmentId());
    }

    public void delete(Bill bill) {
        this.billRepository.delete(bill);
    }

    public void updateBill(Bill bill){
        Bill updatedBill = this.billRepository.findByBankStatmentId(bill.getBankStatmentId());
        updatedBill.setPayments(bill.getPayments());
    }
}
