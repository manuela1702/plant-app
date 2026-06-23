package rs.singidunum.plant.plantapp.service;

import org.springframework.stereotype.Service;
import rs.singidunum.plant.plantapp.entity.PlantSpecies;
import rs.singidunum.plant.plantapp.repository.PlantSpeciesRepository;

import java.util.List;

@Service
public class PlantSpeciesService {

    private final PlantSpeciesRepository repository;

    public PlantSpeciesService(PlantSpeciesRepository repository) {
        this.repository = repository;
    }

    public List<PlantSpecies> getAllSpecies() {
        return repository.findAll();
    }

    public PlantSpecies getSpeciesById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    public PlantSpecies create(PlantSpecies species) {
        return repository.save(species);
    }

    public PlantSpecies update(Integer id, PlantSpecies species) {
        PlantSpecies existing = getSpeciesById(id);

        existing.setName(species.getName());
        existing.setScientificName(species.getScientificName());
        existing.setSunlight(species.getSunlight());
        existing.setWatering(species.getWatering());
        existing.setImageUrl(species.getImageUrl());
        existing.setDescription(species.getDescription());

        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}