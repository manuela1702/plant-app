package rs.singidunum.plant.plantapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.singidunum.plant.plantapp.entity.PlantSpecies;

public interface PlantSpeciesRepository
        extends JpaRepository<PlantSpecies, Integer> {
}