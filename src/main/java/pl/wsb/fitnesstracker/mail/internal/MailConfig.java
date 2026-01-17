package pl.wsb.fitnesstracker.mail.internal;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * Spring configuration class for mail-related components.
 */
@Configuration
@EnableConfigurationProperties(MailProperties.class)
class MailConfig {

}
