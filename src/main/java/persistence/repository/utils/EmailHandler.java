package persistence.repository.utils;



import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailHandler {

    private static final String password = "suev rdhe tqkd mmam";
    private static final String email = "itijets@gmail.com";

    private Session session = null;

    public EmailHandler() {
        setupServerProperties();
    }

    public void sendEmail(String toEmail, String subject, String friendName) {
        MimeMessage mimeMessage = draftEmail(toEmail, subject, friendName);

        String emailHost = "smtp.gmail.com";
        Transport transport = null;

        try {
            transport = session.getTransport("smtp");
            transport.connect(emailHost, email, password);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        } catch (MessagingException e) {
        } finally {
            try {
                if (transport != null) {
                    transport.close();
                }
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private MimeMessage draftEmail(String toEmail, String subject, String friendName) {
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mimeMessage.setSubject(subject);
            mimeMessage.setContent(getHtmlContent(friendName), "text/html");
        } catch (MessagingException e) {
            throw new RuntimeException("Error drafting email", e);
        }
        return mimeMessage;
    }

    private void setupServerProperties() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("smtp.properties")) {

            if (input == null) {
                throw new RuntimeException("Unable to find smtp.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading email.properties", e);
        }
        session = Session.getDefaultInstance(properties, null);
    }

    private static String getHtmlContent(String friendName) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: 'Arial', sans-serif;\n" +
                "            background-color: #f4f4f4;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "\n" +
                "        .email-container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 20px auto;\n" +
                "            background-color: #ffffff;\n" +
                "            padding: 20px;\n" +
                "            border-radius: 10px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "\n" +
                "        h1 {\n" +
                "            color: #333333;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            color: #666666;\n" +
                "        }\n" +
                "\n" +
                "        .button {\n" +
                "            display: inline-block;\n" +
                "            padding: 10px 20px;\n" +
                "            background-color: #3498db;\n" +
                "            color: #ffffff;\n" +
                "            text-decoration: none;\n" +
                "            border-radius: 5px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"email-container\">\n" +
                "        <h1>Hey " + friendName + ",</h1>\n" +
                "        <p>I hope this message finds you well. I wanted to invite you to join me on our Chat App!</p>\n" +
                "        <p>It's a great platform to connect and chat. Click the link below to join:</p>\n" +
                "        <p><a href=\"" + "https://iti.gov.eg" + "\" class=\"button\">Join Chat App</a></p>\n" +
                "        <p>Looking forward to chatting with you!</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }

    public static void main(String[] args) {
        EmailHandler emailHandler = new EmailHandler();
        emailHandler.sendEmail("maher.naser.gh@gmail.com", "Join me on Chat App", "Maher");

    }

}