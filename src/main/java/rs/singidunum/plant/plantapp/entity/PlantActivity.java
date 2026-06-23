package rs.singidunum.plant.plantapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "plant_activity")
@NoArgsConstructor
@Getter
@Setter
public class PlantActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plant_activity_id")
    private Integer id;

    @Column(name = "activity_date", nullable = false)
    private LocalDate activityDate;

    @Column(name = "activity_type", nullable = false)
    private String activityType;

    @ManyToOne
    @JoinColumn(name = "my_plant_id", nullable = false)
    private MyPlant myPlant;
}