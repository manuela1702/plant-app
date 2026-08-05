package rs.singidunum.plant.plantapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.singidunum.plant.plantapp.entity.PlantSpecies;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PlantSpeciesRepository
        extends JpaRepository<PlantSpecies, Integer> {

    List<PlantSpecies> findAllByWatering(PlantSpecies.Watering watering);

    List<PlantSpecies> findAllBySunlight(PlantSpecies.Sunlight sunlight);

    List<PlantSpecies> findAllByNameContainingIgnoreCase(String name);

}