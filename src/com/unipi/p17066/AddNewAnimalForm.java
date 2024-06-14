package com.unipi.p17066;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class AddNewAnimalForm extends JFrame {

    private JPanel addNewAnimalPanel;
    private JButton backToMainMenu;
    private JTextField animalNameTextField;
    private JTextField animalGroupTextField;
    private JTextField averageWeightTextField;
    private JTextField maxLifeTextField;
    private JButton saveNewAnimalButton;

    static Connection connection;
    private static Connection connect(){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:zoo.db");
            return connection;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public static void insertNewAnimal(String animalName, String animalGroup, String averageWeight, String maxAge){
        connection = connect();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement("INSERT INTO animal (id, name, animal_group, weight, average_life_span) VALUES(?,?,?,?,?)");
            statement.setString(2, animalName);
            statement.setString(3, animalGroup);
            statement.setString(4, averageWeight);
            statement.setString(5, maxAge);
            statement.executeUpdate();
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "The new animal has been successfully added to the database.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public AddNewAnimalForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 450));
        setContentPane(addNewAnimalPanel);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setTitle("Attica Zoological Park - Add a new animal to the database");
        ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(img.getImage());

        backToMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuForm menu;
                try {
                    menu = new MainMenuForm();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    throw new RuntimeException(ex);
                }
                menu.setVisible(true);
                dispose();
            }
        });

        saveNewAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertNewAnimal(animalNameTextField.getText(), animalGroupTextField.getText(), averageWeightTextField.getText(), maxLifeTextField.getText());
            }
        });
    }
}
