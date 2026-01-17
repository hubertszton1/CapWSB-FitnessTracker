package pl.wsb.fitnesstracker.user.api;

/**
 * Data Transfer Object representing basic user information.
 * Used for listing all users with limited data exposure.
 * * @param id   The unique identifier of the user.
 * @param name The full name of the user (first name and last name combined).
 */
public record UserSummaryDto(Long id, String name) {
}