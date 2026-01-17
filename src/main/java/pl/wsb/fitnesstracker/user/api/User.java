package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Entity representing a user of the system.
 */
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Setter
public class User {

    /**
     * Unique identifier of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    /**
     * First name of the user.
     */
    @Column
    private String firstName;

    /**
     * Last name of the user.
     */
    @Column
    private String lastName;

    /**
     * Birthdate of the user.
     */
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    /**
     * Email address of the user.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Constructs a {@link User} with the specified details.
     *
     * @param firstName user's first name
     * @param lastName  user's last name
     * @param birthdate user's birthdate
     * @param email     user's email address
     */
    public User(
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }
}