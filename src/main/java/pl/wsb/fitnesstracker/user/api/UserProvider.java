package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return An {@link Optional} containing the all users,
     */
    List<User> findAllUsers();


    /**
     * Retrieves a user by their first and last name.
     *
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @return an {@link Optional} containing the user if found, or {@link Optional#empty()} otherwise
     */
    Optional<User> getUserByFirstAndLastName(String firstName, String lastName);

    /**
     * Retrieves all users whose birthdate is before the specified date.
     *
     * @param date the date to compare birthdates against
     * @return a list of users older than the specified date; may be empty if no users match
     */
    List<User> getAllUsersOlderThan(LocalDate date);


}
