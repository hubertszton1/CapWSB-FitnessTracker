package pl.wsb.fitnesstracker.user.api;

/**
 * Data Transfer Object for user search results by email.
 * Requirement: Returns only user ID and email address.
 * * @param id the unique identifier of the user
 * @param email the email address of the user
 */
public record UserEmailDto(Long id, String email) {
}