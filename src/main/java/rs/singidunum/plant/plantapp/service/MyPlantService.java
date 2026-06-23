package rs.singidunum.plant.plantapp.service;

import org.springframework.stereotype.Service;
import rs.singidunum.plant.plantapp.entity.MyPlant;
import rs.singidunum.plant.plantapp.repository.MyPlantRepository;

import java.util.List;

@Service
public class MyPlantService {

    private final MyPlantRepository repository;

    public MyPlantService(MyPlantRepository repository) {
        this.repository = repository;
    }

    public List<MyPlant> getAllPlants() {
        return repository.findAll();
    }

    public MyPlant getPlantById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    public MyPlant create(MyPlant plant) {
        return repository.save(plant);
    }

    public MyPlant update(Integer id, MyPlant plant) {
        MyPlant existing = getPlantById(id);

        existing.setNickname(plant.getNickname());
        existing.setPlantingDate(plant.getPlantingDate());
        existing.setWateringInterval(plant.getWateringInterval());
        existing.setFertilizingInterval(plant.getFertilizingInterval());
        existing.setRepottingInterval(plant.getRepottingInterval());
        existing.setPlantSpecies(plant.getPlantSpecies());

        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}