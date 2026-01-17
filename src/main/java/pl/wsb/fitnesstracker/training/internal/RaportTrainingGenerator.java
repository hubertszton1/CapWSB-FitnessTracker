package pl.wsb.fitnesstracker.training.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Generates and logs weekly training reports for users.
 */
@Component
@Slf4j
public class RaportTrainingGenerator {

    /**
     * Generates a weekly training report for a specific user.
     * Filters trainings from the last 7 days and displays summary in console.
     *
     * @param user The user for which to generate the report
     * @param trainings All trainings of the user
     */
    public void generateWeeklyReport(User user, List<Training> trainings) {
        if (trainings == null || trainings.isEmpty()) {
            log.info("Weekly Training Report for user: {} (ID: {})", user.getFirstName() + " " + user.getLastName(), user.getId());
            log.info("No trainings recorded in the past week.");
            return;
        }

        // Get current time
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weekAgo = now.minus(7, ChronoUnit.DAYS);

        // Filter trainings from the last 7 days
        List<Training> weeklyTrainings = trainings.stream()
                .filter(training -> {
                    LocalDateTime trainingTime = training.getStartTime()
                            .toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime();
                    return trainingTime.isAfter(weekAgo) && trainingTime.isBefore(now);
                })
                .collect(Collectors.toList());

        // Calculate statistics
        double totalDistance = weeklyTrainings.stream()
                .mapToDouble(Training::getDistance)
                .sum();

        double averageSpeed = weeklyTrainings.stream()
                .mapToDouble(Training::getAverageSpeed)
                .average()
                .orElse(0.0);

        long totalDuration = weeklyTrainings.stream()
                .mapToLong(training -> ChronoUnit.MINUTES.between(
                        training.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                        training.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                ))
                .sum();

        // Log the report
        log.info("=".repeat(80));
        log.info("Weekly Training Report for user: {} (ID: {})", 
                user.getFirstName() + " " + user.getLastName(), user.getId());
        log.info("Period: {} to {}", weekAgo.toLocalDate(), now.toLocalDate());
        log.info("-".repeat(80));
        log.info("Total trainings: {}", weeklyTrainings.size());
        log.info("Total distance: {:.2f} km", totalDistance);
        log.info("Average speed: {:.2f} km/h", averageSpeed);
        log.info("Total duration: {} minutes", totalDuration);
        log.info("-".repeat(80));
        
        // Log individual trainings
        weeklyTrainings.forEach(training -> {
            LocalDateTime startTime = training.getStartTime()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            LocalDateTime endTime = training.getEndTime()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            long duration = ChronoUnit.MINUTES.between(startTime, endTime);
            
            log.info("  {} | {} | Duration: {} min | Distance: {:.2f} km | Avg Speed: {:.2f} km/h",
                    startTime.toLocalDate(),
                    training.getActivityType().getDisplayName(),
                    duration,
                    training.getDistance(),
                    training.getAverageSpeed());
        });
        
        log.info("=".repeat(80));
    }
}
