package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * Data transfer object (DTO) representing basic information about a user.
 * <p>
 * Contains only the user's ID and name details, typically used for lightweight
 * responses where full user information is not required.
 *
 * @param id        the unique identifier of the user, can be {@code null}
 * @param firstName user's first name
 * @param lastName  user's last name
 */
public record UserBasicDto(@Nullable Long id, String firstName, String lastName) {}
