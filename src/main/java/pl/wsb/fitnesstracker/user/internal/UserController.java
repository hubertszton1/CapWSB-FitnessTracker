package pl.wsb.fitnesstracker.user.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.CreateUserDto;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserBasicDto;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller responsible for handling HTTP requests related to user operations.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    /**
     * Retrieves a list of all users.
     *
     * @return list of {@link UserDto} representing all users
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a list of all users with only basic information (ID, first name, last name).
     *
     * @return list of {@link UserBasicDto} containing basic user details
     */
    @GetMapping("/basic")
    public List<UserBasicDto> getAllUsersIdFirstNameLastName() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toBasicDto)
                .toList();
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id ID of the user to retrieve
     * @return {@link ResponseEntity} containing {@link UserDto} if found, or 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves users matching the specified email address.
     *
     * @param email email address to search for
     * @return list of {@link UserDto} matching the given email
     */
    @GetMapping("/email")
    public List<UserDto> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a user by their first and last name.
     *
     * @param firstName first name of the user
     * @param lastName  last name of the user
     * @return {@link ResponseEntity} containing {@link UserDto} if found, or 404 Not Found
     */
    @GetMapping("/name")
    public ResponseEntity<UserDto> getUserByName(
            @RequestParam String firstName,
            @RequestParam String lastName) {
        return userService.getUserByFirstAndLastName(firstName, lastName)
                .map(userMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new user.
     *
     * @param createUserDto DTO containing user creation data
     * @return {@link ResponseEntity} containing the created {@link UserDto} with HTTP status 201 Created
     */
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserDto createUserDto) {
        User user = new User(createUserDto.firstName(), createUserDto.lastName(), createUserDto.birthdate(), createUserDto.email());
        User createdUser = userService.createUser(user);
        UserDto userDto = userMapper.toDto(createdUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userDto);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id ID of the user to delete
     * @return {@link ResponseEntity} with HTTP status 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all users older than the specified date.
     *
     * @param date the date to compare users' birthdates against
     * @return list of {@link UserDto} representing users older than the given date
     */
    @GetMapping("/older/{date}")
    public List<UserDto> getAllUsersOlderThan(
            @PathVariable @JsonFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return userService.getAllUsersOlderThan(date)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Updates an existing user's information.
     *
     * @param userId        ID of the user to update
     * @param updateUserDto DTO containing updated user data
     * @return {@link ResponseEntity} containing the updated {@link UserDto} if the user exists,
     *         or 404 Not Found if the user does not exist
     */
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long userId,
            @RequestBody CreateUserDto updateUserDto) {

        return userService.getUser(userId)
                .map(user -> {
                    user.setFirstName(updateUserDto.firstName());
                    user.setLastName(updateUserDto.lastName());
                    user.setBirthdate(updateUserDto.birthdate());
                    user.setEmail(updateUserDto.email());

                    User updatedUser = userService.saveUser(user);
                    return ResponseEntity.ok(userMapper.toDto(updatedUser));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}