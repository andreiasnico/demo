package map.project.demo.Model.Adapters;

import map.project.demo.Model.Counter;
import map.project.demo.Model.dto.CounterDto;

public class CounterAdapter implements Adapter<Counter, CounterDto> {
    @Override
    public CounterDto transformToDto(Counter concreteObject) {
        return new CounterDto(concreteObject.getCounterId(),
                concreteObject.getCounterTypes(),
                concreteObject.getPricePerUnit(),
                concreteObject.getCheckingDate());
    }
}
