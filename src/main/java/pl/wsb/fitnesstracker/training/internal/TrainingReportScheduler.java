package pl.wsb.fitnesstracker.training.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;

import java.util.List;

/**
 * Scheduled component responsible for generating weekly training reports for all users.
 * Reports are generated automatically and logged to the console.
 */
@Component
@Slf4j
public class TrainingReportScheduler {

    private final UserProvider userProvider;
    private final TrainingServiceImpl trainingService;
    private final RaportTrainingGenerator reportGenerator;

    public TrainingReportScheduler(
            UserProvider userProvider,
            TrainingServiceImpl trainingService,
            RaportTrainingGenerator reportGenerator) {
        this.userProvider = userProvider;
        this.trainingService = trainingService;
        this.reportGenerator = reportGenerator;
    }

    /**
     * Generates weekly training reports for all users.
     * Scheduled to run every Monday at 00:00 (midnight).
     * Cron expression: 0 0 0 ? * MON
     * - 0 0 0 = midnight (00:00:00)
     * - ? = any day of month
     * - * = any month
     * - MON = Monday
     */
    @Scheduled(cron = "0 0 0 ? * MON")
    public void generateWeeklyReportsForAllUsers() {
        log.info("Starting weekly training report generation for all users...");

        List<User> allUsers = userProvider.findAllUsers();

        if (allUsers.isEmpty()) {
            log.info("No users found in the system.");
            return;
        }

        log.info("Found {} users. Generating reports...", allUsers.size());

        for (User user : allUsers) {
            try {
                List<Training> trainings = trainingService.getUserTrainings(user);
                reportGenerator.generateWeeklyReport(user, trainings);
            } catch (Exception e) {
                log.error("Error generating report for user with ID: {}", user.getId(), e);
            }
        }

        log.info("Weekly training report generation completed.");
    }

    /**
     * Alternative scheduler for testing purposes - runs every minute.
     * Uncomment to use instead of the weekly scheduler.
     * This method is disabled by default and can be enabled for development/testing.
     */
    // @Scheduled(fixedDelay = 60000) // Run every 60 seconds
    public void generateReportsForTesting() {
        log.debug("Testing: Generating training reports...");

        List<User> allUsers = userProvider.findAllUsers();

        for (User user : allUsers) {
            try {
                List<Training> trainings = trainingService.getUserTrainings(user);
                reportGenerator.generateWeeklyReport(user, trainings);
            } catch (Exception e) {
                log.error("Error generating report for user with ID: {}", user.getId(), e);
            }
        }
    }
}
