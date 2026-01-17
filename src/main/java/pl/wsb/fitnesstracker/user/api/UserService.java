package pl.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user The user to be created
     * @return The created user
     */
    User createUser(User user);

    /**
     * Update an existing user.
     *
     * @param user The user to be created
     * @return The updated user
     */
    User saveUser(User user);

    /**
     * detele an user by.
     *
     * @param id The user to be created
     */
    void deleteUser(Long id);

}
