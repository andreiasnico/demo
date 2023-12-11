package map.project.demo;

import map.project.demo.Model.Bill;
import map.project.demo.Model.DeliveryMethods;
import map.project.demo.Model.PaymentStatus;
import map.project.demo.Model.Unit;
import map.project.demo.Repository.BillRepository;
import map.project.demo.Service.BillService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BillServiceTest {

    @InjectMocks
    private BillService billService;

    @Mock
    private BillRepository billRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByBillId() {
        // Arrange
        Long billId = 1L;
        Bill mockBill = new Bill();
        when(billRepository.findByBankStatmentId(billId)).thenReturn(mockBill);

        // Act
        Optional<Bill> result = billService.findByBillId(billId);

        // Assert
        verify(billRepository).findByBankStatmentId(billId);
        assertEquals(Optional.of(mockBill), result);
    }

    @Test
    public void testFindAllBills() {
        // Arrange
        List<Bill> mockBills = Arrays.asList(new Bill(), new Bill());
        mockBills.get(0).setDeliveryMethods(DeliveryMethods.Email);
        when(billRepository.findAll()).thenReturn(mockBills);

        // Act
        List<Bill> result = billService.findAllBills();

        // Assert
        verify(billRepository).findAll();
        assertEquals(mockBills, result);
    }

    @Test
    public void testFindAllBillsByUnit() {
        // Arrange
        Unit unit = new Unit();
        List<Bill> mockBills = Arrays.asList(new Bill(), new Bill());
        when(billRepository.findAllByUnit(unit)).thenReturn(mockBills);

        // Act
        List<Bill> result = billService.findAllBillsByUnit(unit);

        // Assert
        verify(billRepository).findAllByUnit(unit);
        assertEquals(mockBills, result);
    }

    @Test
    public void testSave() {
        // Arrange
        Bill bill = new Bill();

        // Act
        Bill result = billService.save(bill);

        // Assert
        verify(billRepository).save(bill);
        assertEquals(bill, result);
    }



}
