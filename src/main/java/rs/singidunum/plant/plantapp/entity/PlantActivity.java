package rs.singidunum.plant.plantapp.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "plant_activity")
@NoArgsConstructor
@Getter
@Setter
public class PlantActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer plantActivityId;

    @Column(nullable = false)
    private LocalDate activityDate;

    public enum ActivityType {
        WATERING,
        FERTILIZING,
        REPOTTING
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActivityType activityType;

    @ManyToOne(optional = false)//N:1
    @JoinColumn(name = "my_plant_id", nullable = false)//foreign key
    private MyPlant myPlant;

}