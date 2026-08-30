package rs.singidunum.plant.plantapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.singidunum.plant.plantapp.entity.PlantSpecies;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PlantSpeciesRepository//defines methods for accessing the table
        extends JpaRepository<PlantSpecies, Integer> {//extends JPA and CRUD, repository that works with the entity

    List<PlantSpecies> findAllByWatering(PlantSpecies.Watering watering);

    List<PlantSpecies> findAllBySunlight(PlantSpecies.Sunlight sunlight);

    List<PlantSpecies> findAllByNameContainingIgnoreCase(String name);

}