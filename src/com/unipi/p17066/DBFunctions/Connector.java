package com.unipi.p17066.DBFunctions;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    Connection connection;
    public Connection connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:zoo.db");
            return connection;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
