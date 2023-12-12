package map.project.demo.Model.dto;

import map.project.demo.Model.CounterTypes;

import java.util.Date;

public class CounterDto {

    private Long counterId;

    private CounterTypes counterTypes;

    private Long pricePerUnit;

    private Date checkingDate;

    public Long getCounterId() {
        return counterId;
    }

    public CounterDto(Long counterId, CounterTypes counterTypes, Long pricePerUnit, Date checkingDate) {
        this.counterId = counterId;
        this.counterTypes = counterTypes;
        this.pricePerUnit = pricePerUnit;
        this.checkingDate = checkingDate;
    }

    public void setCounterId(Long counterId) {
        this.counterId = counterId;
    }

    public CounterTypes getCounterTypes() {
        return counterTypes;
    }

    public void setCounterTypes(CounterTypes counterTypes) {
        this.counterTypes = counterTypes;
    }

    public Long getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Long pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Date getCheckingDate() {
        return checkingDate;
    }

    public void setCheckingDate(Date checkingDate) {
        this.checkingDate = checkingDate;
    }
}
