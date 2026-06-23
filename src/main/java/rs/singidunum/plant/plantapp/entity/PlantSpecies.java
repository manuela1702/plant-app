package rs.singidunum.plant.plantapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "plant_species")
@NoArgsConstructor
@Getter
@Setter
public class PlantSpecies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plant_species_id")
    private Integer id;

    @Column(name = "api_id")
    private Integer apiId;

    @Column(nullable = false)
    private String name;

    @Column(name = "scientific_name")
    private String scientificName;

    private String sunlight;

    private String watering;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String description;
}