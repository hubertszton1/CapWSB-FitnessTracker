package pl.wsb.fitnesstracker.training.api;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;

/**
 * Mapper component responsible for converting {@link User} entities
 * into various Data Transfer Objects (DTOs).
 */
@Component
class TrainingMapper {

    TrainingDto toDto(Training training) {
        return new TrainingDto(training.getUser(), training.getStartTime(), training.getEndTime(), training.getActivityType(), training.getDistance(), training.getAverageSpeed() );
    }

}

