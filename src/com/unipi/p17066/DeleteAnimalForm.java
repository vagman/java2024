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

public class DeleteAnimalForm extends JFrame {

    private JPanel deleteAnimalPanel;
    private JButton backToMainMenu;
    private JButton deleteAnimalButton;
    private JTextField deleteAnimalIDTextField;

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

    public static void deleteAnimal(String id) {
        connection = connect();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement("DELETE FROM animal WHERE id = ?");
            statement.setString(1, id);
            statement.executeUpdate();
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "The animal you selected has been successfully deleted from the database.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            throw new RuntimeException(ex);
        }
    }

    public DeleteAnimalForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 450));
        setContentPane(deleteAnimalPanel);
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



        deleteAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAnimal(deleteAnimalIDTextField.getText());
            }
        });
    }
}
