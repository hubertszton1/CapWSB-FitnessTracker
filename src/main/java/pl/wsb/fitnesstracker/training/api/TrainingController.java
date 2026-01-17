package pl.wsb.fitnesstracker.training.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.internal.TrainingServiceImpl;


import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.stream;

/**
 * REST controller responsible for handling HTTP requests related to user operations.
 * <p>
 * Provides endpoints for creating, retrieving, updating, and deleting users.
 * All endpoints are prefixed with {@code /v1/users}.
 */
@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    @GetMapping
    public List<TrainingDto> getAllUsers() {

        return trainingService.findAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();}

}