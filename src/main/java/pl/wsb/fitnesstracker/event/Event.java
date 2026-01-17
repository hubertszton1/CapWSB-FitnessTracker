package pl.wsb.fitnesstracker.event;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity representing an event.
 */
@Entity
@Table(name = "event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event {

    /**
     * Unique identifier of the event.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the event.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Detailed description of the event.
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /**
     * Date and time when the event starts.
     */
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    /**
     * Date and time when the event ends.
     */
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    /**
     * Country where the event takes place.
     */
    @Column(name = "country")
    private String country;

    /**
     * City where the event takes place.
     */
    @Column(name = "city")
    private String city;

}
