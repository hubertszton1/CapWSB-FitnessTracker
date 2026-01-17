package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.api.TrainingRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    /**
     * Retrieves all trainings for a specific user.
     *
     * @param user The user for which to retrieve trainings
     * @return List of trainings for the given user
     */
    public List<Training> getUserTrainings(User user) {
        return trainingRepository.findAll().stream()
                .filter(training -> training.getUser() != null && training.getUser().getId().equals(user.getId()))
                .toList();
    }
}

