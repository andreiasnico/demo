package map.project.demo;

import map.project.demo.Model.*;
import map.project.demo.Repository.BillRepository;
import map.project.demo.Service.BillService;
import map.project.demo.Service.Commanders.BillCommander;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestPatterns {
    @Mock
    private BillService billService;

    @Mock
    private BillRepository billRepository;
    @Mock
    private Bill bill;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * test for the observer pattern
     */
    @Test
    public void testObserver(){

        Payment payment = new Payment();
        Reading reading = new Reading();
        List<Reading> readings = new ArrayList<>();
        readings.add(reading);

        bill.setBillId(1L);
        payment.setBill(bill);

        payment.setAmount(1000L);
        List<Payment> payments = new ArrayList<>();
        payments.add(payment);
        bill.setPayments(payments);
        assert (payment.getBillStatus() != BillStatus.Payed);
        Mockito.when(bill.getReadings()).thenReturn(readings);
        Mockito.when(bill.getAllReadingsSum() ).thenReturn(1000L);
        bill.notifyPayments();
        assert (payment.getBillStatus() == BillStatus.Payed);
    }


    @Test
    public void testCommander(){
        Bill bill = new Bill();
        bill.setBillId(1L);
        assert (bill.getPaymentStatus() != PaymentStatus.Payed);
        List<Bill> bills = new ArrayList<>();
        bills.add(bill);
        this.billService.addBill(bill);
        bill.setPaymentStatus(PaymentStatus.Pending);
        Mockito.when(this.billService.findByBillId(1L)).thenReturn(Optional.of(bill));
        Mockito.when(this.billService.findAllBills()).thenReturn(bills);
        BillCommander commander = BillCommander.getInstance(this.billService);

        commander.execute();
        assert (this.billService.findByBillId(1L).get().getPaymentStatus() == PaymentStatus.Pending);
    }
}
