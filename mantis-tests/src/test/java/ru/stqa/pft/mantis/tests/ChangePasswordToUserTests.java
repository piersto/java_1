package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import static org.testng.AssertJUnit.assertTrue;

public class ChangePasswordToUserTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePasswordToUser() throws IOException, MessagingException {
        // Open Login page
        app.admin().openLoginPage();
        // Login as administrator
        app.admin().login();
        // Go to http://localhost/mantisbt-2.25.0/manage_user_page.php
        app.admin().openMangeUsersPage();
        // Click on user that is not administrator
        app.admin().initPasswordChange("piersto");
        // Сохранить его username and email
        String email = "pstoiko@gmail.com";
        String password = "pass";
        String user = "user";

        // Найти среди всех писем то, которое было отправлено на нужный адрес,
        // пройти по ссылке, изменить пароль
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 30000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        // Сначала нужно найти среди всех писем то, которое было отправлено на нужный адрес:
        // mailMessages.stream()
        MailMessage mailMessage = mailMessages.stream()
                .filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://")
                                                        .nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    // alwaysRun = true чтобы сервер останавливался даже если тест упал
    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
