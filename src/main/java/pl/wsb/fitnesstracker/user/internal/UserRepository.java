package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Spring Data JPA repository for {@link User} entities.
 */
interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    /**
     * Finds a user by first and last name.
     *
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @return {@link Optional} containing the found user, or {@link Optional#empty()} if not found
     */
    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Finds all users whose birthdate is before the specified date.
     *
     * @param date the date to compare users' birthdates against
     * @return list of users born before the specified date
     */
    List<User> findAllByBirthdateBefore(LocalDate date);
}
