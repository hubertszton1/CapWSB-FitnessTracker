package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserBasicDto;
import pl.wsb.fitnesstracker.user.api.UserDto;

/**
 * Mapper component responsible for converting {@link User} entities
 * into various Data Transfer Objects (DTOs).
 */
@Component
class UserMapper {
    /**
     * Converts a {@link User} entity to a {@link UserDto}.
     *
     * @param user the user entity to convert
     * @return {@link UserDto} containing full user details
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    /**
     * Converts a {@link User} entity to a {@link UserBasicDto}.
     * <p>
     * This DTO contains only basic user information: ID, first name, and last name.
     *
     * @param user the user entity to convert
     * @return {@link UserBasicDto} containing basic user details
     */
    UserBasicDto toBasicDto(User user) {
        return new UserBasicDto(user.getId(), user.getFirstName(), user.getLastName());
    }
}
