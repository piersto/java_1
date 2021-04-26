package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ChangePasswordToUserTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    public List<String> dbConnection() {
        Connection conn = null;
        try {
            conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery
                    ("select username, email from mantis_user_table");

            while (rs.next()) {
                List<String> user = new ArrayList<>();
                String str = rs.getString("username");
                String strEmail = rs.getString("email");
                user.add(str);
                user.add(strEmail);
                if (!"administrator".equalsIgnoreCase(str))
                    return user;
            }
            rs.close();
            st.close();
            conn.close();
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    @Test
    public void testChangePasswordToUser() throws IOException, MessagingException {
        String userFromDb = dbConnection().get(0);
        String emailFromDb = dbConnection().get(1);
        System.out.println(userFromDb);
        System.out.println(emailFromDb);
        String password = "pass";

        app.admin().openLoginPage();
        app.admin().login();
        app.admin().openMangeUsersPage();
        app.admin().initPasswordChange(userFromDb);
        app.admin().logout();

        // Найти среди всех писем то, которое было отправлено на нужный адрес, пройти по ссылке, изменить пароль
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 30000);
        String confirmationLink = findConfirmationLink(mailMessages, emailFromDb);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(userFromDb, password));
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
