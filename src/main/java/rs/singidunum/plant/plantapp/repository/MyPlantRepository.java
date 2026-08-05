package rs.singidunum.plant.plantapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.singidunum.plant.plantapp.entity.MyPlant;

import java.util.List;

@Repository
public interface MyPlantRepository
        extends JpaRepository<MyPlant, Integer> {

    List<MyPlant> findAllByNicknameContainingIgnoreCase(String nickname);

    List<MyPlant> findAllByPlantSpeciesPlantSpeciesId(Integer id);
}