package pl.wsb.fitnesstracker.training.internal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.training.api.TrainingRepository;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;

import java.util.List;

/**
 * TrainingController is responsible for handling HTTP requests related to training operations.
 * It provides endpoints for retrieving trainings.
 */
@RestController
@RequestMapping("/v1/trainings")
class TrainingController {

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;
    private final UserProvider userProvider;

    public TrainingController(TrainingRepository trainingRepository, TrainingMapper trainingMapper, UserProvider userProvider) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
        this.userProvider = userProvider;
    }

    /**
     * Gets all trainings.
     *
     * @return List of all trainings as DTOs
     */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingRepository.findAll()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Gets all trainings for a specific user.
     *
     * @param userId The ID of the user
     * @return List of trainings for the given user as DTOs
     */
    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsForUser(@PathVariable Long userId) {
        User user = userProvider.getUser(userId).orElse(null);
        if (user == null) {
            return List.of();
        }

        return trainingRepository.findAll()
                .stream()
                .filter(training -> training.getUser() != null && training.getUser().getId().equals(userId))
                .map(trainingMapper::toDto)
                .toList();
    }
}
