package rs.singidunum.plant.plantapp.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "my_plant")
@NoArgsConstructor//lombok
@Getter
@Setter
public class MyPlant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer myPlantId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private LocalDate plantingDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plant_species_id", nullable = false)
    private PlantSpecies plantSpecies;//object for accessing all data
}