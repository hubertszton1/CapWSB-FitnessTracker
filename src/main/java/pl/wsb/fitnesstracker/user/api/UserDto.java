package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

/**
 * Data transfer object (DTO) representing full information about a user.
 * <p>
 * Typically used for API responses where all basic user details are required.
 *
 * @param id        the unique identifier of the user, can be {@code null}
 * @param firstName user's first name
 * @param lastName  user's last name
 * @param birthdate user's date of birth in yyyy-MM-dd format
 * @param email     user's email address
 */
public record UserDto(@Nullable Long id, String firstName, String lastName,
                      @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
                      String email) {
}
