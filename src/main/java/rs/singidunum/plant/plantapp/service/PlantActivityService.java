package rs.singidunum.plant.plantapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.singidunum.plant.plantapp.entity.PlantActivity;
import rs.singidunum.plant.plantapp.repository.PlantActivityRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlantActivityService {

    private final PlantActivityRepository repository;

    public List<PlantActivity> getAllActivities() {
        return repository.findAll();
    }

    public Optional<PlantActivity> getActivityById(Integer id) {
        return repository.findById(id);
    }

    public PlantActivity create(PlantActivity activity) {
        PlantActivity plantActivity = new PlantActivity();

        plantActivity.setActivityDate(activity.getActivityDate());
        plantActivity.setActivityType(activity.getActivityType());
        plantActivity.setMyPlant(activity.getMyPlant());// Sets the plant that the activity belongs to

        return repository.save(plantActivity);
    }

    public PlantActivity update(Integer id, PlantActivity activity) {
        PlantActivity existing = getActivityById(id).orElseThrow();

        existing.setActivityDate(activity.getActivityDate());
        existing.setActivityType(activity.getActivityType());
        existing.setMyPlant(activity.getMyPlant());

        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<PlantActivity> getByMyPlantId(Integer id) {
        return repository.findAllByMyPlantMyPlantId(id);
    }

    public Optional<PlantActivity> getLastActivity(
            Integer myPlantId,
            PlantActivity.ActivityType activityType) {

        return repository
                .findFirstByMyPlantMyPlantIdAndActivityTypeOrderByActivityDateDesc(
                        myPlantId,
                        activityType
                );
    }
}