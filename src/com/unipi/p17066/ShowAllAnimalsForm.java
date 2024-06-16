package com.unipi.p17066;

import com.unipi.p17066.DBFunctions.Connector;
import com.unipi.p17066.DBFunctions.DBFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class ShowAllAnimalsForm extends JFrame {
    private JPanel showAllAnimalsPanel;
    private JButton backToMainMenu;
    private JTable animalsTable;
    private JScrollPane animalScrollPane;

    Connector connector = new Connector();
    void populateAnimalsTable() {

        try {
            assert connector.connect() != null;
            PreparedStatement statement = connector.connect().prepareStatement("SELECT * FROM animal");

            // Created a method for ShowAllAnimalsForm and SearchByIDForm in order to avoid duplicate code (DRY)
            DBFunctions functions = new DBFunctions();
            functions.select(statement, animalsTable, animalScrollPane);

            statement.close();
            connector.connect().close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        }
    }

    public ShowAllAnimalsForm() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 450));
        setContentPane(showAllAnimalsPanel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Attica Zoological Park - Show All Animals");
        ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(img.getImage());

        populateAnimalsTable();

        backToMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuForm menu;
                try {
                    menu = new MainMenuForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                menu.setVisible(true);
                dispose();
            }
        });
    }

}
