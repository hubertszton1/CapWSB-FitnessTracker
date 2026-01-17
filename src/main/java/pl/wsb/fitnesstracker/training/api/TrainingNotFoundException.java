package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Exception indicating that the {@link Training} was not found.
 */
@SuppressWarnings("squid:S110")
public class TrainingNotFoundException extends NotFoundException {
    /**
     * Private constructor allowing custom message.
     *
     * @param message the exception message
     */
    private TrainingNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a {@link TrainingNotFoundException} for a given training ID.
     *
     * @param id the ID of the training that was not found
     */
    public TrainingNotFoundException(Long id) {
        this("Training with ID=%s was not found".formatted(id));
    }
}