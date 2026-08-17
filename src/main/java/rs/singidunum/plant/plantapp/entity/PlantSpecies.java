package rs.singidunum.plant.plantapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "plant_species")
@NoArgsConstructor
@Getter
@Setter
public class PlantSpecies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer plantSpeciesId;

    @Column(nullable = false)
    private String name;

    private String scientificName;

    public enum Sunlight {
        LOW,
        HIGH
    }

    public enum Watering {
        LOW,
        HIGH
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sunlight sunlight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Watering watering;

    private String imageUrl;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @Column(nullable = false)
    private Integer wateringInterval;

    @Column(nullable = false)
    private Integer fertilizingInterval;

    @Column(nullable = false)
    private Integer repottingInterval;
}