package rs.singidunum.plant.plantapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.singidunum.plant.plantapp.entity.MyPlant;

public interface MyPlantRepository
        extends JpaRepository<MyPlant, Integer> {
}