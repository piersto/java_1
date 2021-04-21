package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import java.sql.*;

public class DbConnectionTest {

    @Test
    public void testDbConnection() {
        Connection conn = null;

        try {
            conn = DriverManager
                            .getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=");

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery
                    ("select username from mantis_user_table");
            /*
            List<String> usernames = new List<String>() ;
            while (rs.next()) {
                usernames.add(new List<>().withId(rs.getInt("group_id"))
                        .withName(rs.getString("group_name"))
                        .withHeader(rs.getString("group_header"))
                        .withFooter(rs.getString("group_footer")));
            }
            */
            rs.close();

            st.close();
            conn.close();
            //System.out.println(groups);


            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
