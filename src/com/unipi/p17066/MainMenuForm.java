package com.unipi.p17066;

import com.unipi.p17066.DBFunctions.Connector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class MainMenuForm extends JFrame {
    private JPanel mainMenuPanel;
    private JButton showAllAvailableAnimalsButton;
    private JButton addNewAnimalButton;
    private JButton searchAnimalByNameButton;
    private JButton searchAnimalByIDButton;
    private JButton editAnimalByIDButton;
    private JButton deleteAnAnimalByButton;
    private JButton exitApplicationButton;

    Connector connector = new Connector();
    void createAnimalsTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS animal (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT NOT NULL, ANIMAL_GROUP TEXT NOT NULL, WEIGHT NUMERIC NOT NULL, AVERAGE_LIFE_SPAN INTEGER NOT NULL);";
        try {
            Statement statement = connector.connect().createStatement();
            statement.execute(createTableSQL);
            statement.close();
            connector.connect().close();
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
                SearchAnimalByNameForm searchAnimalByNameForm;
                searchAnimalByNameForm = new SearchAnimalByNameForm();
                searchAnimalByNameForm.setVisible(true);
                setVisible(false);
            }
        });

        searchAnimalByIDButton.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    SearchAnimalByIDForm searchAnimalByIDForm;
                    searchAnimalByIDForm = new SearchAnimalByIDForm();
                    searchAnimalByIDForm.setVisible(true);
                    setVisible(false);
                }
        });

        editAnimalByIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditAnimalForm editAnimalForm;
                editAnimalForm = new EditAnimalForm();
                editAnimalForm.setVisible(true);
                setVisible(false);
            }
        });

        deleteAnAnimalByButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteAnimalForm deleteAnimalForm;
                deleteAnimalForm = new DeleteAnimalForm();
                deleteAnimalForm.setVisible(true);
                setVisible(false);
            }
        });

        exitApplicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // On Exit terminate all open JFrames
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
