package rs.singidunum.plant.plantapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.singidunum.plant.plantapp.entity.PlantActivity;

public interface PlantActivityRepository
        extends JpaRepository<PlantActivity, Integer> {
}