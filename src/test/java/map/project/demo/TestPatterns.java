package map.project.demo;

import map.project.demo.Model.*;
import map.project.demo.Repository.BillRepository;
import map.project.demo.Service.BillService;
import map.project.demo.Service.Commanders.BillCommander;
import map.project.demo.Service.PaymentService;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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
    private PaymentService paymentService;

    @Mock
    private Bill bill;

    @Mock
    private Payment payment;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * test for the observer pattern
     */
    @Test
    public void testObserver(){
        //create objects
        bill.setBillId(1L);
        payment.setBill(bill);
        assert (payment.getBillStatus() != BillStatus.Payed);
        Answer<Payment> answer = new Answer<Payment>() {
            @Override
            public Payment answer(InvocationOnMock invocationOnMock) throws Throwable {
                payment.updateStatus();
                return payment;
            }
        };
        Mockito.doAnswer(answer).when(bill).notifyPayments();
        assert(payment.getBillStatus() == BillStatus.Payed);

    }

    /**
     * test for the commander pattern
     */
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
