package com.unipi.p17066;

import com.unipi.p17066.DBFunctions.Connector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    Connector connector = new Connector();
    public void insertNewAnimal() {
        try {
            assert connector.connect() != null;
            PreparedStatement statement = connector.connect().prepareStatement("INSERT INTO animal (id, name, animal_group, weight, average_life_span) VALUES(?,?,?,?,?)");
            statement.setString(2, animalNameTextField.getText());
            statement.setString(3, animalGroupTextField.getText());
            statement.setString(4, averageWeightTextField.getText());
            statement.setString(5, maxLifeTextField.getText());
            statement.executeUpdate();
            statement.close();
            connector.connect().close();
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
                insertNewAnimal();
            }
        });
    }
}
