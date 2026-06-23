package rs.singidunum.plant.plantapp.controller;

import org.springframework.web.bind.annotation.*;
import rs.singidunum.plant.plantapp.entity.PlantActivity;
import rs.singidunum.plant.plantapp.service.PlantActivityService;

import java.util.List;

@RestController
public class PlantActivityController {

    private final PlantActivityService service;

    public PlantActivityController(PlantActivityService service) {
        this.service = service;
    }

    @GetMapping("/activities")
    public List<PlantActivity> getAllActivities() {
        return service.getAllActivities();
    }

    @GetMapping("/activities/{id}")
    public PlantActivity getActivityById(@PathVariable Integer id) {
        return service.getActivityById(id);
    }

    @PostMapping("/activities")
    public PlantActivity create(@RequestBody PlantActivity activity) {
        return service.create(activity);
    }

    @PutMapping("/activities/{id}")
    public PlantActivity update(@PathVariable Integer id,
                                @RequestBody PlantActivity activity) {
        return service.update(id, activity);
    }

    @DeleteMapping("/activities/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}