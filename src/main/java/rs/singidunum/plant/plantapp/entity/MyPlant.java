package rs.singidunum.plant.plantapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "my_plant")
@NoArgsConstructor
@Getter
@Setter
public class MyPlant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_plant_id")
    private Integer id;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "planting_date", nullable = false)
    private LocalDate plantingDate;

    @Column(name = "watering_interval", nullable = false)
    private Integer wateringInterval;

    @Column(name = "fertilizing_interval", nullable = false)
    private Integer fertilizingInterval;

    @Column(name = "repotting_interval", nullable = false)
    private Integer repottingInterval;

    @ManyToOne
    @JoinColumn(name = "plant_species_id", nullable = false)
    private PlantSpecies plantSpecies;

}