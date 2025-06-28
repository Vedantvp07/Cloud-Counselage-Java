import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    public static void sendEmail(String to, String name, String utmLink) {
        final String senderEmail = "..";  //your-email@gmail.com
        final String senderPassword = "..";  // 16 char app password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Welcome to IAC Community, " + name);
            message.setText("Hi " + name + ",\n\nWelcome! Here's your UTM link:\n" + utmLink + "\n\nRegards,\nIAC Team");

            Transport.send(message);
            System.out.println("Email sent to " + to);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
