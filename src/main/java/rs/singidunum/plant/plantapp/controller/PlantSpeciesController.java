package rs.singidunum.plant.plantapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.singidunum.plant.plantapp.entity.PlantSpecies;
import rs.singidunum.plant.plantapp.service.PlantSpeciesService;

import java.util.List;

@RestController//rest controller, receives http requests and returns json responses
@RequestMapping("/species")//base path for all methods
@CrossOrigin
@RequiredArgsConstructor
public class PlantSpeciesController {

    private final PlantSpeciesService service;

    @GetMapping
    public List<PlantSpecies> getAllSpecies() {
        return service.getAllSpecies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantSpecies> getSpeciesById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getSpeciesById(id));
    }

    @PostMapping
    public PlantSpecies createSpecies(@RequestBody PlantSpecies species) {
        return service.create(species);
    }

    @PutMapping("/{id}")
    public PlantSpecies update(@PathVariable Integer id,
                               @RequestBody PlantSpecies species) {
        return service.update(id, species);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/watering/{watering}")
    public List<PlantSpecies> getByWatering(
            @PathVariable PlantSpecies.Watering watering) {//reads the parameter from the URL
        return service.getByWatering(watering);
    }

    @GetMapping("/sunlight/{sunlight}")
    public List<PlantSpecies> getBySunlight(
            @PathVariable PlantSpecies.Sunlight sunlight) {
        return service.getBySunlight(sunlight);
    }

    @GetMapping("/search/{name}")
    public List<PlantSpecies> searchByName(@PathVariable String name) {
        return service.searchByName(name);
    }
}