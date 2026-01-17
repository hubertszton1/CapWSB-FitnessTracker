package pl.wsb.fitnesstracker.workoutsession;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.wsb.fitnesstracker.training.api.Training;

import java.time.LocalDateTime;

/**
 * Entity representing a single workout session linked to a {@link Training}.
 */
@Entity
@Table(name = "workout_session")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkoutSession {

    /**
     * Unique identifier of the workout session.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Training associated with this workout session.
     * <p>
     * Many-to-one relationship with {@link Training}.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    /**
     * Timestamp of when this workout session was recorded.
     */
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    /**
     * Starting latitude coordinate of the session.
     */
    @Column(name = "start_latitude", nullable = false)
    private double startLatitude;

    /**
     * Starting longitude coordinate of the session.
     */
    @Column(name = "start_longitude", nullable = false)
    private double startLongitude;

    /**
     * Ending latitude coordinate of the session.
     */
    @Column(name = "end_latitude")
    private Double endLatitude;

    /**
     * Ending longitude coordinate of the session.
     */
    @Column(name = "end_longitude")
    private Double endLongitude;

    /**
     * Altitude during the workout session.
     */
    @Column(name = "altitude")
    private Double altitude;
}