package application_controller.graphic;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class EmailGraphicController {
    private EmailGraphicController() {
        throw new IllegalStateException("Utility class");
    }

    public static Message sendEmail() {


        Properties p = new Properties();

        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", "587");
        p.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        String myEmail = "mybarber@gmail.com";
        String myPassword = "fantozzi";

        Session session = Session.getInstance(p, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, myPassword);
            }
        });

        return prepareMessage(session, myEmail);
    }


    public static Message prepareMessage(Session session, String myEmail) {
        Message mex = new MimeMessage(session);
        try{

            mex.setFrom(new InternetAddress(myEmail));

            mex.setRecipient(Message.RecipientType.TO, new InternetAddress(myEmail));



        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return mex;

    }
}
