package map.project.demo.Service;

import map.project.demo.Model.Unit;
import map.project.demo.Repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitService {
    @Autowired
    private UnitRepository unitRepository;

    public Optional<Unit> findByUnitId(Long unitId) {

        return unitRepository.findByUnitId(unitId);
    }

    public Unit save(Unit unit) {
        unitRepository.save(unit);
        return unit;
    }

    public List<Unit> findAll() {
        return unitRepository.findAll();
    }

    public void delete(Unit unit) {
        unitRepository.delete(unit);
    }

    public void addUnit(Unit unit) {
        unitRepository.save(unit);
    }

    public void updateUnit(Unit unit) {
        Unit updatedUnit = unitRepository.findById(unit.getUnitId()).get();
        updatedUnit.setUnitId(unit.getUnitId());
        unitRepository.save(updatedUnit);
    }

    public void readUnit(Unit unit) {
        unitRepository.findById(unit.getUnitId());
    }


    public Object findAllUnits() {
        return findAll();
    }
}
