package map.project.demo.Service;

import map.project.demo.Model.Bill;
import map.project.demo.Model.Payment;
import map.project.demo.Model.PaymentStatus;
import map.project.demo.Model.Unit;
import map.project.demo.Repository.BillRepository;
import map.project.demo.Repository.PaymentRepository;
import map.project.demo.Repository.ReadingRepository;
import map.project.demo.Repository.UnitRepository;
import map.project.demo.Service.Commanders.BillCommander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
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

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ReadingRepository readingRepository;
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
        this.billRepository.save(updatedBill);
    }

    public void addPaymentToBill(Bill bill , Payment payment){
        List<Payment>   payments = bill.getPayments();
        payments.add(payment);
        bill.setPayments(payments);
        this.billRepository.save(bill);
    }

    /**
     * stream to get the amount necessary to pay the bill
     *
     * @return Long sum
     */
    public Long getAllReadingsSum(Bill bill) {
        return this.readingRepository.findAllByBill(bill).stream()
                .map(elem -> Pair.of(elem.getCounter().getPricePerUnit(), elem.getVolumeReading()))
                .toList().stream().map(value -> value.getFirst() * value.getSecond())
                .reduce(0L, Long::sum);
    }

    /**
     * observer pattern implementation
     * this is the subject (Bill) that notifies the Observers ( Payment) when the amount stacked into payments
     * is enough to pay the bill , the payments will receive the notification to change the bill status
     */
    public void notifyPayments(Bill bill) {
        Long sum = 0L;
        for (Payment payment : bill.getPayments()) {
            sum += payment.getAmount();
        }
        if(sum >= getAllReadingsSum(bill)){
            for(Payment payment :  bill.getPayments()){
                payment.updateStatus();
                this.paymentRepository.save(payment);
            }
        }
        bill.setPaymentStatus(PaymentStatus.Payed);
        this.billRepository.save(bill);
    }
}
