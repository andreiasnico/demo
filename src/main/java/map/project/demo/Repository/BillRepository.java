package map.project.demo.Repository;

import map.project.demo.Model.Bill;
import map.project.demo.Model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    Bill findByBillId(Long id);

    List<Bill> findAllByUnit(Unit unit);



    //Todo further implementation of all we need
}
