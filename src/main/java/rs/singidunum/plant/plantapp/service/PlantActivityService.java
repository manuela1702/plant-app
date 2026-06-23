package rs.singidunum.plant.plantapp.service;

import org.springframework.stereotype.Service;
import rs.singidunum.plant.plantapp.entity.PlantActivity;
import rs.singidunum.plant.plantapp.repository.PlantActivityRepository;

import java.util.List;

@Service
public class PlantActivityService {

    private final PlantActivityRepository repository;

    public PlantActivityService(PlantActivityRepository repository) {
        this.repository = repository;
    }

    public List<PlantActivity> getAllActivities() {
        return repository.findAll();
    }

    public PlantActivity getActivityById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    public PlantActivity create(PlantActivity activity) {
        return repository.save(activity);
    }

    public PlantActivity update(Integer id, PlantActivity activity) {
        PlantActivity existing = getActivityById(id);

        existing.setActivityDate(activity.getActivityDate());
        existing.setActivityType(activity.getActivityType());
        existing.setMyPlant(activity.getMyPlant());

        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}