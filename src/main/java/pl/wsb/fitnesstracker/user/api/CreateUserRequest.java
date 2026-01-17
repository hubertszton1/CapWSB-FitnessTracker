package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

/**
 * DTO for creating or updating a user.
 */
public record CreateUserRequest(
        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotNull(message = "Birth date is required")
        @Past(message = "Birth date must be in the past")
        @JsonFormat(pattern = "yyyy-MM-dd")
        @JsonProperty("birthdate") // Dopasowanie do testów integracyjnych
        LocalDate birthDate,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email
) {
}