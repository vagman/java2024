package com.unipi.p17066;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

public class SearchAnimalByNameForm extends JFrame {
    private JPanel searchAnimalByNamePanel;
    private JButton backToMainMenu;
    private JScrollPane searchedAnimalByNameScrollPane;
    private JTable searchedAnimalTable;
    private JTextField searchAnimalNameTextField;
    private JButton searchAnimalByNameButton;

    static Connection connection;
    private static Connection connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:zoo.db");
            return connection;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    void searchAnimalByName(String id) {
        connection = connect();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM animal WHERE name = ?");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) {
                JOptionPane.showMessageDialog(null, "The animal you selected was not found in the database.", "NOT FOUND", JOptionPane.INFORMATION_MESSAGE);
            } else {
                searchedAnimalTable.setModel(DbUtils.resultSetToTableModel(resultSet));
                searchedAnimalTable.setRowHeight(50);
                DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
                cellRenderer.setHorizontalAlignment(JLabel.CENTER);
                for (int i = 0; i < searchedAnimalTable.getColumnCount(); i++) {
                    searchedAnimalTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
                }
                searchedAnimalByNameScrollPane.setViewportView(searchedAnimalTable);

                String[] header = {"ID", "Animal name", "Animal Group", "Average weight", "Average lifespan"};
                for(int i = 0; i < searchedAnimalTable.getColumnCount(); i++) {
                    TableColumn c = searchedAnimalTable.getTableHeader().getColumnModel().getColumn(i);
                    c.setHeaderValue(header[i]);
                }
                searchedAnimalTable.getTableHeader().setFont( new Font( "Arial" , Font.BOLD, 18));
                JOptionPane.showMessageDialog(null, "The animal you selected has been found!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            throw new RuntimeException(ex);
        }
    }

    public SearchAnimalByNameForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 500));
        setContentPane(searchAnimalByNamePanel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Attica Zoological Park - Search animal by name");
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
        searchAnimalByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAnimalByName(searchAnimalNameTextField.getText());
            }
        });
    }

}
