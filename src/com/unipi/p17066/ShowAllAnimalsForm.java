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

public class ShowAllAnimalsForm extends JFrame {

    private JPanel showAllAnimalsPanel;
    private JButton backToMainMenu;
    private JTable animalsTable;
    private JScrollPane animalScrollPane;

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

    void populateAnimalsTable() {
        connection = connect();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM animal");
            ResultSet resultSet = statement.executeQuery();
            animalsTable.setModel(DbUtils.resultSetToTableModel(resultSet));
            animalsTable.setRowHeight(50);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            for (int i = 0; i < animalsTable.getColumnCount(); i++) {
                animalsTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
            }
            animalScrollPane.setViewportView(animalsTable);

            String[] header = {"ID", "Animal name", "Animal Group", "Average weight", "Average lifespan"};
            for(int i=0;i<animalsTable.getColumnCount();i++) {
                TableColumn column1 = animalsTable.getTableHeader().getColumnModel().getColumn(i);
                column1.setHeaderValue(header[i]);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
        }
    }

    public ShowAllAnimalsForm() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 450));
        setContentPane(showAllAnimalsPanel);
        setVisible(true);
        pack();
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
