package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * Data transfer object (DTO) for creating a new {@link User}.
 * <p>
 * Contains basic user information required for registration.
 *
 * @param firstName user's first name
 * @param lastName  user's last name
 * @param birthdate user's date of birth in yyyy-MM-dd format
 * @param email     user's email address
 */
public record CreateUserDto(
        String firstName,
        String lastName,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
        String email) {
}