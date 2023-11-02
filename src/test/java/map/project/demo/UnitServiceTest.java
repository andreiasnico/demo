package map.project.demo;

import map.project.demo.Model.Unit;
import map.project.demo.Repository.UnitRepository;
import map.project.demo.Service.UnitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UnitServiceTest {

    @InjectMocks
    private UnitService unitService;

    @Mock
    private UnitRepository unitRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByUnitId() {
        // Arrange
        Long unitId = 1L;
        Unit mockUnit = new Unit();
        when(unitRepository.findByUnitId(unitId)).thenReturn(Optional.of(mockUnit));

        // Act
        Optional<Unit> result = unitService.findByUnitId(unitId);

        // Assert
        verify(unitRepository).findByUnitId(unitId);
        assertEquals(Optional.of(mockUnit), result);
    }

    @Test
    public void testSave() {
        // Arrange
        Unit unit = new Unit();

        // Act
        Unit result = unitService.save(unit);

        // Assert
        verify(unitRepository).save(unit);
        assertEquals(unit, result);
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<Unit> mockUnits = Arrays.asList(new Unit(), new Unit());
        when(unitRepository.findAll()).thenReturn(mockUnits);

        // Act
        Iterable<Unit> result = unitService.findAll();

        // Assert
        verify(unitRepository).findAll();
        assertEquals(mockUnits, (List<Unit>) result);
    }

    @Test
    public void testDelete() {
        // Arrange
        Unit unit = new Unit();

        // Act
        unitService.delete(unit);

        // Assert
        verify(unitRepository).delete(unit);
    }
}
