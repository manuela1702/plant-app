package rs.singidunum.plant.plantapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.singidunum.plant.plantapp.entity.PlantActivity;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlantActivityRepository
        extends JpaRepository<PlantActivity, Integer> {

    List<PlantActivity> findAllByMyPlantMyPlantId(Integer id);

    Optional<PlantActivity>
    findFirstByMyPlantMyPlantIdAndActivityTypeOrderByActivityDateDesc(//the last activity of that type for that plant
            Integer id,
            PlantActivity.ActivityType activityType
    );

    void deleteAllByMyPlantMyPlantId(Integer id);
}