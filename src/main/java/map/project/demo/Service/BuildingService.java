package map.project.demo.Service;
import map.project.demo.Model.Unit;
import map.project.demo.Repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
public class BuildingService {
    @Autowired
    private UnitRepository unitRepository;

    public Optional<Unit> findByUnitId(Long unitId){

        return unitRepository.findByUnitId(unitId);
    }

    public Unit save(Unit unit){
        unitRepository.save(unit);
        return unit;
    }

    public void delete(Unit unit){
        unitRepository.delete(unit);
    }


}
