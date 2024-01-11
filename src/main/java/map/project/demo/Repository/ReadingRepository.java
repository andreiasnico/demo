package map.project.demo.Repository;

import map.project.demo.Model.Bill;
import map.project.demo.Model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReadingRepository extends JpaRepository<Reading,Long> {

        Optional<Reading> findByReadingId(Long id);

        boolean removeByReadingId(Long id);

        //Todo further implementation of all we need

        List<Reading> findAllByBill(Bill bill);
}
