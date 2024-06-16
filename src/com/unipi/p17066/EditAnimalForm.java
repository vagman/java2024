package com.unipi.p17066;

import com.unipi.p17066.DBFunctions.Connector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class EditAnimalForm extends JFrame {
    private JPanel editAnimalForm;
    private JButton backToMainMenu;
    private JButton searchAnimalByIDButton;
    private JButton savedChangesButton;
    private JTextField animalIDToBeEditedTextField;
    private JTextField animalAgeTextField;
    private JTextField animalWeightTextField;
    private JTextField animalIDTextField;
    private JTextField animalGroupTextField;
    private JTextField animalNameTextField;

    Connector connector = new Connector();
    public void searchAnimalByID(String id) {
        try {
            assert connector.connect() != null;
            PreparedStatement statement = connector.connect().prepareStatement("SELECT * FROM animal WHERE id = ?");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            StringBuilder builder = new StringBuilder();
            while (resultSet.next()) {
                builder.append(resultSet.getString("id"))
                        .append("\n")
                        .append(resultSet.getString("name"))
                        .append("\n")
                        .append(resultSet.getString("animal_group"))
                        .append("\n")
                        .append(resultSet.getString("weight"))
                        .append("\n")
                        .append(resultSet.getString("average_life_span"))
                        .append("\n");
            }
            String[] newLineSeparatedValues = builder.toString().split("\n");
            animalIDTextField.setText(newLineSeparatedValues[0]);
            animalNameTextField.setText(newLineSeparatedValues[1]);
            animalGroupTextField.setText(newLineSeparatedValues[2]);
            animalWeightTextField.setText(newLineSeparatedValues[3]);
            animalAgeTextField.setText(newLineSeparatedValues[4]);
            JOptionPane.showMessageDialog(null, "The animal you selected has been found!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            statement.close();
            connector.connect().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            throw new RuntimeException(ex);
        }
    }
    public void editAnimalInfo(int id, String animalName, String animalGroup, float averageWeight, int maxAge) {
        try {
            assert connector.connect() != null;
            PreparedStatement statement = connector.connect().prepareStatement("UPDATE animal SET name = ?, animal_group = ?, weight = ?, average_life_span = ? WHERE id = ?");
            statement.setString(1, animalName);
            statement.setString(2, animalGroup);
            statement.setFloat(3, averageWeight);
            statement.setInt(4, maxAge);
            statement.setInt(5, id);
            statement.executeUpdate();
            statement.close();
            connector.connect().close();
            JOptionPane.showMessageDialog(null, "The changes you made to " + animalName + " have been saved to the database.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public EditAnimalForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1200, 600));
        setContentPane(editAnimalForm);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setTitle("Attica Zoological Park - Edit information of an already existing animal by searching it with its ID");
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

        searchAnimalByIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAnimalByID(animalIDToBeEditedTextField.getText());
            }
        });


        savedChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editAnimalInfo(Integer.parseInt(animalIDTextField.getText()), animalNameTextField.getText(), animalGroupTextField.getText(), Float.parseFloat(animalWeightTextField.getText()), Integer.parseInt(animalAgeTextField.getText()));

            }
        });
    }
}
