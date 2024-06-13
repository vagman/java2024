package com.unipi.p17066;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;
import net.proteanit.sql.DbUtils;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

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

    void showAllAnimalsForm() {
        connection = connect();
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM animal");
            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet);
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

        showAllAnimalsForm();

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
