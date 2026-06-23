package rs.singidunum.plant.plantapp.controller;

import org.springframework.web.bind.annotation.*;
import rs.singidunum.plant.plantapp.entity.PlantSpecies;
import rs.singidunum.plant.plantapp.service.PlantSpeciesService;

import java.util.List;

@RestController
public class PlantSpeciesController {

    private final PlantSpeciesService service;

    public PlantSpeciesController(PlantSpeciesService service) {
        this.service = service;
    }

    @GetMapping("/species")
    public List<PlantSpecies> getAllSpecies() {
        return service.getAllSpecies();
    }
    @GetMapping("/species/{id}")
    public PlantSpecies getSpeciesById(@PathVariable Integer id) {
        return service.getSpeciesById(id);
    }

    @PostMapping("/species")
    public PlantSpecies create(@RequestBody PlantSpecies species) {
        return service.create(species);
    }

    @PutMapping("/species/{id}")
    public PlantSpecies update(@PathVariable Integer id,
                               @RequestBody PlantSpecies species) {
        return service.update(id, species);
    }

    @DeleteMapping("/species/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}