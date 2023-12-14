package map.project.demo;

import map.project.demo.Model.*;
import map.project.demo.Model.Adapters.AdapterFacade;
import map.project.demo.Model.Adapters.BillAdapter;
import map.project.demo.Model.Adapters.BuildingAdapter;
import map.project.demo.Model.Adapters.CounterAdapter;
import map.project.demo.Model.dto.BillDto;
import map.project.demo.Model.dto.BuildingDto;
import map.project.demo.Model.dto.CounterDto;
import map.project.demo.Repository.BillRepository;
import map.project.demo.Service.BillService;
import map.project.demo.Service.Commanders.BillCommander;
import map.project.demo.Service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.*;

public class TestPatterns {
    @Mock
    private BillService billService;

    @Mock
    private BillRepository billRepository;

    @Mock
    private PaymentService paymentService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * test for the observer pattern
     */
    @Test
    public void testObserver() {
        Bill bill = new Bill();
        Reading reading = new Reading();
        Counter counter = new Counter();
        List<Reading> readings = new ArrayList<>();
        List<Payment> payments = new ArrayList<>();
        Payment payment = new Payment();

        bill.setBankStatmentId(1L);
        payment.setBill(bill);
        payment.setAmount(1000L);
        payments.add(payment);
        counter.setPricePerUnit(2);
        reading.setBill(bill);
        reading.setVolumeReading(500L);
        reading.setCounter(counter);
        readings.add(reading);
        bill.setReadings(readings);
        bill.setPayments(payments);

        assert (payment.getPaymentStatus() != PaymentStatus.Payed);
        bill.notifyPayments();
        assert (payment.getPaymentStatus() == PaymentStatus.Payed);


    }

    /**
     * test for the commander pattern
     * directly checking singleton
     */
    @Test
    public void testCommander() {
        Bill bill = new Bill();
        bill.setBankStatmentId(1L);
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

    /**
     * test for the adapter pattern
     * we will change the type of object we will get
     */
    @Test
    public void testAdapter() {
        /**
         * checking for bill
         */
        Bill bill = new Bill();
        bill.setBankStatmentId(1L);
        bill.setDeliveryMethods(DeliveryMethods.Email);
        bill.setPaymentStatus(PaymentStatus.Pending);

        BillAdapter billAdapter = new BillAdapter();

        BillDto billDto = billAdapter.transformToDto(bill);

        assert (Objects.equals(billDto.getBillId(), bill.getBankStatmentId()));
        assert (billDto.getStatus() == bill.getPaymentStatus());
        assert (billDto.getDeliveryMethod() == bill.getDeliveryMethods());

        /**
         * checking for Building
         */
        Building building = new Building();
        building.setName("Central");
        building.setBuildingId(1L);
        building.setStreet("Crisan");
        building.setTown("Cluj");
        building.setNumberOfStories(3);

        BuildingAdapter buildingAdapter = new BuildingAdapter();
        BuildingDto buildingDto = buildingAdapter.transformToDto(building);

        assert (Objects.equals(buildingDto.getBuildingId(), building.getBuildingId()));
        assert (Objects.equals(buildingDto.getName(), building.getName()));
        assert (Objects.equals(buildingDto.getStreet(), building.getStreet()));
        assert (Objects.equals(buildingDto.getTown(), building.getTown()));
        assert (buildingDto.getNumberOfStories() == building.getNumberOfStories());

        /**
         * checking for Counter
         */
        Date date = new Date(2023 , 11 ,11);
        Counter counter = new Counter();
        counter.setCounterId(1L);
        counter.setPricePerUnit(20L);
        counter.setCounterTypes(CounterTypes.Gas);
        counter.setCheckingDate(date);

        CounterAdapter counterAdapter = new CounterAdapter();
        CounterDto counterDto = counterAdapter.transformToDto(counter);

        assert (counterDto.getCounterId().equals(counter.getCounterId()));
        assert (counterDto.getCounterTypes() == counter.getCounterTypes());
        assert (counterDto.getCheckingDate().equals(counter.getCheckingDate()));
        assert (counterDto.getPricePerUnit().equals(counter.getPricePerUnit()));

    }

    /**
     * test method for checking the facade design pattern
     */
    @Test
    public void testFacade(){
        Bill bill = new Bill();
        bill.setBankStatmentId(1L);
        bill.setDeliveryMethods(DeliveryMethods.Email);
        bill.setPaymentStatus(PaymentStatus.Pending);

        BillDto billDto = (BillDto) AdapterFacade.adaptToDto(bill , Bill.class);

        assert (Objects.equals(billDto.getBillId(), bill.getBankStatmentId()));
        assert (billDto.getStatus() == bill.getPaymentStatus());
        assert (billDto.getDeliveryMethod() == bill.getDeliveryMethods());
    }



}