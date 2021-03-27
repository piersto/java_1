package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        String email = "user1@localhost.localdomain";
        app.registration().start("user1", email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 30000);
        findConfirmationLink(mailMessages, email);


    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {

        // Сначала нужно найти среди всех писем то, которое было отправлено на нужный адрес: mailMessages.stream()
        MailMessage mailMessage = mailMessages.stream()
                .filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    // alwaysRun = true чтобы сервер останавливался даже если тест упал
    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
