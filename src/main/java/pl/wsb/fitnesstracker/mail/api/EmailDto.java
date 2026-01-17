package pl.wsb.fitnesstracker.mail.api;

/**
 * Data transfer object representing an email message.
 *
 * @param toAddress email address
 * @param subject   subject of the email
 * @param content   body content of the email
 */
public record EmailDto(String toAddress, String subject, String content) {

}
