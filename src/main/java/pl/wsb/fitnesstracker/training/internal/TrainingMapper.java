package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.user.internal.UserMapper;

/**
 * Mapper for converting Training entities to TrainingDto and vice versa.
 */
@Component
public class TrainingMapper {

    private final UserMapper userMapper;

    public TrainingMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * Converts a Training entity to a TrainingDto.
     *
     * @param training The training entity
     * @return The training DTO
     */
    public TrainingDto toDto(Training training) {
        if (training == null) {
            return null;
        }

        return new TrainingDto(
                training.getId(),
                userMapper.toDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    /**
     * Converts a TrainingDto to a Training entity.
     *
     * @param trainingDto The training DTO
     * @return The training entity
     */
    public Training toEntity(TrainingDto trainingDto) {
        if (trainingDto == null) {
            return null;
        }

        Training training = new Training(
                userMapper.toEntity(trainingDto.getUser()),
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed()
        );

        if (trainingDto.getId() != null) {
            training.setId(trainingDto.getId());
        }

        return training;
    }
}
