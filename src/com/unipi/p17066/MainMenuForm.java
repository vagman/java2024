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

public class MainMenuForm extends JFrame {

    private static boolean programRunningFirstTime = true;
    static Connection connection;

    private JPanel mainMenuPanel;
    private JButton showAllAvailableAnimalsButton;
    private JButton addNewAnimalButton;
    private JButton searchAnimalByNameButton;
    private JButton searchAnimalByIDButton;
    private JButton editAnimalByIDButton;
    private JButton deleteAnAnimalByButton;
    private JButton exitApplicationButton;

    private static Connection connect(){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:zoo.db");
            if (programRunningFirstTime) {
                JOptionPane.showMessageDialog(null, "Connection to the zoo database was successful.", "Connection", JOptionPane.INFORMATION_MESSAGE);
                programRunningFirstTime = false;
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void createAnimalsTable() {
        connection = connect();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS animal (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT UNIQUE NOT NULL, ANIMAL_GROUP TEXT NOT NULL, WEIGHT NUMERIC NOT NULL, AVERAGE_LIFE_SPAN INTEGER NOT NULL);";
        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSQL);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        }
    }

    public MainMenuForm() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(700, 500));
        setContentPane(mainMenuPanel);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Attica Zoological Park - Main Menu");
        ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(img.getImage());

        createAnimalsTable();

        showAllAvailableAnimalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowAllAnimalsForm showAllAnimalsForm = null;
                try {
                    showAllAnimalsForm = new ShowAllAnimalsForm();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, e);
                }
                assert showAllAnimalsForm != null;
                showAllAnimalsForm.setVisible(true);
                setVisible(false);
            }
        });

        addNewAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewAnimalForm addNewAnimal;
                addNewAnimal = new AddNewAnimalForm();
                addNewAnimal.setVisible(true);
                setVisible(false);
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
                // Terminate all open JFrames
                for (int i = 0; i < Frame.getFrames().length; i++) {
                    Frame.getFrames()[i].dispose();
                }
            }
            });

    }

    public static void main(String[] args) throws SQLException {
        new MainMenuForm();
    }
}
