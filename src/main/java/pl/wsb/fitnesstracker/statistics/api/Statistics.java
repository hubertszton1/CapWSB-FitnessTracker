package pl.wsb.fitnesstracker.statistics.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

/**
 * Entity representing aggregated statistics for a {@link User}.
 */
@Entity
@Table(name = "Statistics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Statistics {

    /**
     * Unique identifier of the statistics record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    /**
     * User associated with these statistics.
     */
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    /**
     * Total number of trainings the user has completed.
     */
    @Column(name = "total_trainings", nullable = false)
    private int totalTrainings;

    /**
     * Total distance (in kilometers) covered by the user in all trainings.
     */
    @Column(name = "total_distance")
    private double totalDistance;

    /**
     * Total calories burned by the user across all trainings.
     */
    @Column(name = "total_calories_burned")
    private int totalCaloriesBurned;

    /**
     * Constructs a {@link Statistics} record with the given values.
     *
     * @param id                  optional ID of the record
     * @param user                associated user
     * @param totalTrainings      total number of trainings
     * @param totalDistance       total distance covered
     * @param totalCaloriesBurned total calories burned
     */
    public Statistics(@Nullable Long id, User user, int totalTrainings, double totalDistance, int totalCaloriesBurned) {
        this.id = id;
        this.user = user;
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }
}