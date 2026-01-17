package pl.wsb.fitnesstracker.event;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wsb.fitnesstracker.user.api.User;

/**
 * Entity representing the relationship between a {@link User} and an {@link Event}.
 */
@Entity
@Table(name = "user_event",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "event_id"}))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEvent {
    /**
     * Unique identifier of the user-event association.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User participating in the event.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Event in which the user participates.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    /**
     * Status of the user's participation in the event
     */
    @Column(name = "status")
    @Setter
    private String status;
}


