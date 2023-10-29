package map.project.demo.Repository;

import map.project.demo.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill , Long> {

    List<Bill> findAllByBillId(Long id);

   //Todo further implementation of all we need
}
