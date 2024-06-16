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

public class SearchAnimalByIDForm extends JFrame {
    private JPanel searchAnimalByIDPanel;
    private JButton backToMainMenu;
    private JButton searchAnimalByIDButton;
    private JTextField searchAnimalIDTextField;
    private JTable searchedAnimalTable;
    private JScrollPane searchedAnimalScrollPane;

    Connector connector = new Connector();
    void searchAnimalByID(String id) {
        try {
            PreparedStatement statement = connector.connect().prepareStatement("SELECT * FROM animal WHERE id = ?");
            statement.setString(1, id);

            // Created a method for ShowAllAnimalsForm and SearchByIDForm in order to avoid duplicate code (DRY)
            DBFunctions functions = new DBFunctions();
            functions.select(statement, searchedAnimalTable, searchedAnimalScrollPane);

            searchedAnimalTable.getTableHeader().setFont( new Font( "Arial" , Font.BOLD, 18));
            JOptionPane.showMessageDialog(null, "The animal you selected has been found!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

            statement.close();
            connector.connect().close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            throw new RuntimeException(ex);
        }
    }

    public SearchAnimalByIDForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 500));
        setContentPane(searchAnimalByIDPanel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Attica Zoological Park - Search animal by ID");
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
                searchAnimalByID(searchAnimalIDTextField.getText());
            }
        });
    }
}
