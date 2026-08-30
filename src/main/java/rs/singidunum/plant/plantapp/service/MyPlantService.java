package rs.singidunum.plant.plantapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.singidunum.plant.plantapp.entity.MyPlant;
import rs.singidunum.plant.plantapp.repository.MyPlantRepository;
import rs.singidunum.plant.plantapp.repository.PlantActivityRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyPlantService {

    private final MyPlantRepository repository;
    private final PlantActivityRepository plantActivityRepository;//for deleting a plant activity

    public List<MyPlant> getAllPlants() {
        return repository.findAll();
    }

    public Optional<MyPlant> getPlantById(Integer id) {
        return repository.findById(id);
    }

    public MyPlant create(MyPlant plant) {
        MyPlant myPlant = new MyPlant();

        myPlant.setNickname(plant.getNickname());
        myPlant.setPlantingDate(plant.getPlantingDate());
        myPlant.setPlantSpecies(plant.getPlantSpecies());

        return repository.save(myPlant);
    }

    public MyPlant update(Integer id, MyPlant plant) {
        MyPlant existing = getPlantById(id).orElseThrow();

        existing.setNickname(plant.getNickname());
        existing.setPlantingDate(plant.getPlantingDate());
        existing.setPlantSpecies(plant.getPlantSpecies());

        return repository.save(existing);
    }

    @Transactional//database integrity
    public void delete(Integer id) {
        plantActivityRepository.deleteAllByMyPlantMyPlantId(id);
        repository.deleteById(id);
    }

    public List<MyPlant> searchByNickname(String nickname) {
        return repository.findAllByNicknameContainingIgnoreCase(nickname);
    }

    public List<MyPlant> getByPlantSpeciesId(Integer id) {
        return repository.findAllByPlantSpeciesPlantSpeciesId(id);
    }
}