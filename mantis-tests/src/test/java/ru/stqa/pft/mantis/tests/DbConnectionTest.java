package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

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

            List ll = new LinkedList();
            while (rs.next()) {
                String str = rs.getString("username");
                ll.add(str);
            }
            ListIterator<String> listIterator = ll.listIterator();
            rs.close();
            st.close();
            conn.close();
            System.out.println(ll);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
