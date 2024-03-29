package map.project.demo;

import map.project.demo.Model.*;
import map.project.demo.Model.Adapters.AdapterProxy;
import map.project.demo.Model.Adapters.BillAdapter;
import map.project.demo.Model.Adapters.BuildingAdapter;
import map.project.demo.Model.Adapters.CounterAdapter;
import map.project.demo.Model.dto.BillDto;
import map.project.demo.Model.dto.Builder.*;
import map.project.demo.Model.dto.BuildingDto;
import map.project.demo.Model.dto.CounterDto;
import map.project.demo.Repository.BillRepository;
import map.project.demo.Repository.PaymentRepository;
import map.project.demo.Repository.ReadingRepository;
import map.project.demo.Service.BillService;
import map.project.demo.Service.Commanders.BillCommander;
import map.project.demo.Service.PaymentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.Date;
import java.util.*;

public class TestPatterns {
    @InjectMocks
    private BillService billService;

    @Mock
    private BillService mockBillService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private BillRepository billRepository;

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private ReadingRepository readingRepository;


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
        payment.setBankStatmentId(2L);
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
        Mockito.doReturn(readings).when(this.readingRepository).findAllByBill(bill);
       System.out.println( this.billService.getAllReadingsSum(bill));
        Mockito.when(this.billRepository.findByBankStatmentId(1L)).thenReturn(bill);
        //Mockito.when(this.paymentRepository.findAllByBill(bill)).thenReturn(payments);
        Mockito.doReturn(payments).when(this.paymentRepository).findAllByBill(bill);
        Mockito.doReturn(payment).when(this.paymentRepository).save(payment);
//        Mockito.when(this.billService.findByBillId(payment.getBill().getBankStatmentId())).thenReturn(Optional.of(bill));
        Mockito.doReturn(Optional.of(bill)).when(this.mockBillService).findByBillId(1L);
        this.paymentService.addPayment(payment);
        //todo idk why repo is not called up
        System.out.println(payment.getPaymentStatus());
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
        Date date = new Date(2023, 11, 11);
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
    public void testFacade() {
        Bill bill = new Bill();
        bill.setBankStatmentId(1L);
        bill.setDeliveryMethods(DeliveryMethods.Email);
        bill.setPaymentStatus(PaymentStatus.Pending);

        BillDto billDto = (BillDto) AdapterProxy.adaptToDto(bill, Bill.class);

        assert (Objects.equals(billDto.getBillId(), bill.getBankStatmentId()));
        assert (billDto.getStatus() == bill.getPaymentStatus());
        assert (billDto.getDeliveryMethod() == bill.getDeliveryMethods());
    }


    @Test
    public void testBuilder() {
        Unit unit = new Unit();
        unit.setUnitId(1L);
        unit.setName("saragosa");

        Renter renter = new Renter();
        renter.setRenterId(1L);
        renter.setFirmName("Soraga");
        unit.setRenter(renter);
        Bill bill = new Bill();
        bill.setBankStatmentId(1L);
        bill.setDeliveryMethods(DeliveryMethods.Email);
        bill.setPaymentStatus(PaymentStatus.Pending);
        bill.setUnit(unit);


        Counter waterCounter = new Counter();
        waterCounter.setCounterId(1L);
        waterCounter.setPricePerUnit(10L);
        waterCounter.setCounterTypes(CounterTypes.Water);

        Counter gasCounter = new Counter();
        gasCounter.setCounterId(2L);
        gasCounter.setCounterTypes(CounterTypes.Gas);
        gasCounter.setPricePerUnit(8L);

        Reading reading = new Reading();
        reading.setReadingId(1L);
        reading.setCounter(waterCounter);
        reading.setBill(bill);
        reading.setVolumeReading(11L);

        Reading reading1 = new Reading();
        reading1.setVolumeReading(12L);
        reading1.setBill(bill);
        reading1.setCounter(gasCounter);
        reading1.setReadingId(2L);

        List<Reading> readings = new ArrayList<>();
        readings.add(reading);
        readings.add(reading1);

        bill.setReadings(readings);


        BillInformationBuilder billInformationBuilder = new BillInformationBuilder();
        BillInformationDto billInformationDto = billInformationBuilder.buildObject(bill);

        Assertions.assertEquals(billInformationDto.getBillId(), bill.getBankStatmentId());
        Assertions.assertEquals(billInformationDto.getDeliveryMethods(), bill.getDeliveryMethods());
        Assertions.assertEquals(billInformationDto.getPaymentStatus(), bill.getPaymentStatus());

        BillUnitInformationBuilder billUnitInformationBuilder = new BillUnitInformationBuilder();
        BillUnitInformationDto billUnitInformationDto = billUnitInformationBuilder.buildObject(bill);

        Assertions.assertEquals(billUnitInformationDto.getBillId(), bill.getBankStatmentId());
        Assertions.assertEquals(billUnitInformationDto.getDeliveryMethod(), bill.getDeliveryMethods());
        Assertions.assertEquals(billUnitInformationDto.getPaymentStatus(), bill.getPaymentStatus());
        Assertions.assertEquals(billUnitInformationDto.getUnitId(), bill.getUnit().getUnitId());
        Assertions.assertEquals(billUnitInformationDto.getUnitName(), bill.getUnit().getName());

        BillRenterInformationBuilder billRenterInformationBuilder = new BillRenterInformationBuilder();
        BillRenterInformationDto billRenterInformationDto = billRenterInformationBuilder.buildObject(bill);

        Assertions.assertEquals(billRenterInformationDto.getBillId(), bill.getBankStatmentId());
        Assertions.assertEquals(billRenterInformationDto.getDeliveryMethod(), bill.getDeliveryMethods());
        Assertions.assertEquals(billRenterInformationDto.getPaymentStatus(), bill.getPaymentStatus());
        Assertions.assertEquals(billRenterInformationDto.getUnitId(), bill.getUnit().getUnitId());
        Assertions.assertEquals(billRenterInformationDto.getUnitName(), bill.getUnit().getName());
        Assertions.assertEquals(billRenterInformationDto.getRenterId(), bill.getUnit().getRenter().getRenterId());
        Assertions.assertEquals(billRenterInformationDto.getRenterName(), bill.getUnit().getRenter().getFirmName());
    }

}