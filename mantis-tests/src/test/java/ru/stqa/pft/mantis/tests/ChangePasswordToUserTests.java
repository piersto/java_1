package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static org.testng.AssertJUnit.assertTrue;

public class ChangePasswordToUserTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    public String dbConnection() {
        Connection conn = null;
        try {
            conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery
                    ("select username from mantis_user_table");

            List ll = new LinkedList();
            while (rs.next()) {
                String str = rs.getString("username");
                ll.add(str);
            }
            ListIterator<String> listIterator = ll.listIterator();
            while (listIterator.hasNext()) {
                if (!listIterator.next().equals("administrator")) {

                    String user = listIterator.next();
                    return user;
                }
            }
            rs.close();
            st.close();
            conn.close();

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
    }

    @Test
    public void testChangePasswordToUser() throws IOException, MessagingException {
        String user = "piersto";
        String email = user+"@localhost.localdomain";
        String password = "pass";
        //String user = "piersto";
        app.admin().openLoginPage();
        app.admin().login();
        app.admin().openMangeUsersPage();
        app.admin().initPasswordChange(user);
        app.admin().logout();

        // Найти среди всех писем то, которое было отправлено на нужный адрес,
        // пройти по ссылке, изменить пароль
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 30000);
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
