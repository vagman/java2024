package com.unipi.p17066;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import static javax.management.remote.JMXConnectorFactory.connect;

public class MainMenuForm extends JFrame {

    private JPanel mainMenuPanel;
    private JButton showAllAvailableAnimalsButton;
    private JButton addNewAnimalButton;
    private JButton searchAnimalByNameButton;
    private JButton searchAnimalByIDButton;
    private JButton editAnimalByIDButton;
    private JButton deleteAnAnimalByButton;
    private JButton exitApplicationButton;

    //Βήμα 1. ConnectionString
    static String connectionString = "jdbc:sqlite:zoo.db";

    //Βήμα 2. Connection object
    static Connection connection;

    // Βήμα 3. Instantiate connection object
    private static Connection connect(){
        try {
            connection = DriverManager.getConnection(connectionString);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void createAnimalsTable() {
        connection = connect();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS animal (ID INTEGER, NAME TEXT, ANIMAL_GROUP TEXT, WEIGHT NUMERIC, AVERAGE_LIFE_SPAN INTEGER); INSERT INTO animal VALUES (0, 'Elephant', 'Mammals', 6910.0, 56)";
        // Δημιουργία αντικειμένου Statement
        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSQL);
            statement.close();
            connection.close();
            System.out.println("Done!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public MainMenuForm() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 700));
        setContentPane(mainMenuPanel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Attica Zoological Park - Main Menu");
        ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(img.getImage());
        createAnimalsTable();

        showAllAvailableAnimalsButton.addActionListener(e -> {

        });

        addNewAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        searchAnimalByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        searchAnimalByIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        editAnimalByIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        deleteAnAnimalByButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        exitApplicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuForm.this.dispose();
            }
        });
    }

    public static void main(String[] args) throws SQLException {
        new MainMenuForm();
    }
    

}
