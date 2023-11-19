package map.project.demo.Repository;

import map.project.demo.Model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReadingRepository extends JpaRepository<Reading,Long> {

        Reading findByReadingId(Long id);

        boolean removeByReadingId(Long id);


        //Todo further implementation of all we need
}
