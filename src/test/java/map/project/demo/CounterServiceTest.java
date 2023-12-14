package map.project.demo;

import map.project.demo.Model.Counter;
import map.project.demo.Repository.CounterRepository;
import map.project.demo.Service.CounterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CounterServiceTest {
    @InjectMocks
    private CounterService counterService;

    @Mock
    private CounterRepository counterRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllCounters() {
        // Arrange
        List<Counter> mockCounters = Arrays.asList(new Counter(), new Counter());
        mockCounters.get(0).setCounterId(1L);
        when(counterRepository.findAll()).thenReturn(mockCounters);

        // Act
        List<Counter> result = counterService.findAllCounters();

        // Assert
        verify(counterRepository).findAll();
        assertEquals(mockCounters, result);
    }

    @Test
    public void testFindCounterById() {
        // Arrange
        Long counterId = 1L;
        Counter mockCounter = new Counter();
        mockCounter.setCounterId(counterId);
        when(counterRepository.findByCounterId(counterId)).thenReturn(Optional.of(mockCounter));

        // Act
        Counter result = counterService.findCounterById(counterId);

        // Assert
        verify(counterRepository).findByCounterId(counterId);
        assertEquals(mockCounter, result);
    }

    @Test
    public void testSave() {
        // Arrange
        Counter mockCounter = new Counter();
        when(counterRepository.save(mockCounter)).thenReturn(mockCounter);

        // Act
        Counter result = counterService.save(mockCounter);

        // Assert
        verify(counterRepository).save(mockCounter);
        assertEquals(mockCounter, result);
    }






}
