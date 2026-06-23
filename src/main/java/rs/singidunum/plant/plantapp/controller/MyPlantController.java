package rs.singidunum.plant.plantapp.controller;

import org.springframework.web.bind.annotation.*;
import rs.singidunum.plant.plantapp.entity.MyPlant;
import rs.singidunum.plant.plantapp.service.MyPlantService;

import java.util.List;

@RestController
public class MyPlantController {

    private final MyPlantService service;

    public MyPlantController(MyPlantService service) {
        this.service = service;
    }

    @GetMapping("/plants")
    public List<MyPlant> getAllPlants() {
        return service.getAllPlants();
    }

    @GetMapping("/plants/{id}")
    public MyPlant getPlantById(@PathVariable Integer id) {
        return service.getPlantById(id);
    }

    @PostMapping("/plants")
    public MyPlant create(@RequestBody MyPlant plant) {
        return service.create(plant);
    }

    @PutMapping("/plants/{id}")
    public MyPlant update(@PathVariable Integer id,
                          @RequestBody MyPlant plant) {
        return service.update(id, plant);
    }

    @DeleteMapping("/plants/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}