package pl.wsb.fitnesstracker.training.api;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;

/**
 * Entity representing a training session of a {@link User}.
 */
@Entity
@Table(name = "trainings")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Training {

    /**
     * Unique identifier of the training record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User associated with this training session.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Start time of the training session.
     */
    @Column(name = "start_time", nullable = false)
    private Date startTime;

    /**
     * End time of the training session.
     */
    @Column(name = "end_time", nullable = false)
    private Date endTime;

    /**
     * Type of activity performed during the training.
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;

    /**
     * Distance covered during the training (e.g., in kilometers).
     */
    @Column(name = "distance")
    private double distance;

    /**
     * Average speed during the training (e.g., km/h).
     */
    @Column(name = "average_speed")
    private double averageSpeed;

    /**
     * Constructs a {@link Training} session with the given parameters.
     *
     * @param user         the user performing the training
     * @param startTime    start time of the training
     * @param endTime      end time of the training
     * @param activityType type of activity
     * @param distance     distance covered during the training
     * @param averageSpeed average speed during the training
     */
    public Training(
            final User user,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }

}