package rs.singidunum.plant.plantapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.singidunum.plant.plantapp.entity.MyPlant;
import rs.singidunum.plant.plantapp.service.MyPlantService;

import java.util.List;

@RestController
@RequestMapping("/plants")
@CrossOrigin
@RequiredArgsConstructor
public class MyPlantController {

    private final MyPlantService service;

    @GetMapping
    public List<MyPlant> getAllPlants() {
        return service.getAllPlants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MyPlant> getPlantById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getPlantById(id));
    }

    @PostMapping
    public MyPlant createPlant(@RequestBody MyPlant plant) {
        return service.create(plant);
    }

    @PutMapping("/{id}")
    public MyPlant updatePlant(@PathVariable Integer id,
                               @RequestBody MyPlant plant) {
        return service.update(id, plant);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlantById(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/search/{nickname}")
    public List<MyPlant> searchByNickname(@PathVariable String nickname) {
        return service.searchByNickname(nickname);
    }

    @GetMapping("/species/{id}")
    public List<MyPlant> getByPlantSpeciesId(@PathVariable Integer id) {
        return service.getByPlantSpeciesId(id);
    }
}