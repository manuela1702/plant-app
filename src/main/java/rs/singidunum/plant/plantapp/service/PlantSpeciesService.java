package rs.singidunum.plant.plantapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.singidunum.plant.plantapp.entity.PlantSpecies;
import rs.singidunum.plant.plantapp.repository.PlantSpeciesRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlantSpeciesService {

    private final PlantSpeciesRepository repository;

    public List<PlantSpecies> getAllSpecies() {
        return repository.findAll();
    }

    public Optional<PlantSpecies> getSpeciesById(Integer id) {
        return repository.findById(id);
    }

    public PlantSpecies create(PlantSpecies species) {
        PlantSpecies plantSpecies = new PlantSpecies();

        plantSpecies.setName(species.getName());
        plantSpecies.setScientificName(species.getScientificName());
        plantSpecies.setSunlight(species.getSunlight());
        plantSpecies.setWatering(species.getWatering());
        plantSpecies.setImageUrl(species.getImageUrl());
        plantSpecies.setDescription(species.getDescription());

        plantSpecies.setWateringInterval(species.getWateringInterval());
        plantSpecies.setFertilizingInterval(species.getFertilizingInterval());
        plantSpecies.setRepottingInterval(species.getRepottingInterval());

        return repository.save(plantSpecies);
    }

    public PlantSpecies update(Integer id, PlantSpecies species) {
        PlantSpecies existing = getSpeciesById(id).orElseThrow();

        existing.setName(species.getName());
        existing.setScientificName(species.getScientificName());
        existing.setSunlight(species.getSunlight());
        existing.setWatering(species.getWatering());
        existing.setImageUrl(species.getImageUrl());
        existing.setDescription(species.getDescription());

        existing.setWateringInterval(species.getWateringInterval());
        existing.setFertilizingInterval(species.getFertilizingInterval());
        existing.setRepottingInterval(species.getRepottingInterval());

        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<PlantSpecies> getByWatering(PlantSpecies.Watering watering) {
        return repository.findAllByWatering(watering);
    }

    public List<PlantSpecies> getBySunlight(PlantSpecies.Sunlight sunlight) {
        return repository.findAllBySunlight(sunlight);
    }

    public List<PlantSpecies> searchByName(String name) {
        return repository.findAllByNameContainingIgnoreCase(name);
    }
}