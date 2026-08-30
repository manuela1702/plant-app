package rs.singidunum.plant.plantapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.singidunum.plant.plantapp.entity.PlantActivity;
import rs.singidunum.plant.plantapp.service.PlantActivityService;

import java.util.List;

@RestController
@RequestMapping("/activities")
@CrossOrigin
@RequiredArgsConstructor
public class PlantActivityController {

    private final PlantActivityService service;

    @GetMapping
    public List<PlantActivity> getAllActivities() {
        return service.getAllActivities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantActivity> getActivityById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getActivityById(id));
    }

    @PostMapping
    public PlantActivity createActivity(@RequestBody PlantActivity activity) {
        return service.create(activity);
    }

    @PutMapping("/{id}")
    public PlantActivity updateActivity(@PathVariable Integer id,
                                        @RequestBody PlantActivity activity) {
        return service.update(id, activity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteActivityById(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/plant/{id}")
    public List<PlantActivity> getByMyPlantId(@PathVariable Integer id) {
        return service.getByMyPlantId(id);
    }

    @GetMapping("/plant/{id}/last/{activityType}")
    public ResponseEntity<PlantActivity> getLastActivity(
            @PathVariable Integer id,
            @PathVariable PlantActivity.ActivityType activityType) {

        return ResponseEntity.of(//returns the entire http response,body and status
                service.getLastActivity(id, activityType)
        );
    }
}