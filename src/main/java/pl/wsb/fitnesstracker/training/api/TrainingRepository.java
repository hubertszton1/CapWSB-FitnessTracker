package pl.wsb.fitnesstracker.training.api;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for {@link Training} entities.
 */
public interface TrainingRepository extends JpaRepository<Training, Long> {
}
