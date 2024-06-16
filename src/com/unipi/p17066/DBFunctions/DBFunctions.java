package com.unipi.p17066.DBFunctions;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBFunctions {
    public void select(PreparedStatement statement, JTable dataJTable, JScrollPane animalJScrollPane) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        dataJTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        dataJTable.setRowHeight(50);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < dataJTable.getColumnCount(); i++) {
            dataJTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        animalJScrollPane.setViewportView(dataJTable);

        String[] header = {"ID", "Animal name", "Animal Group", "Average weight", "Average lifespan"};
        for(int i=0; i < dataJTable.getColumnCount(); i++) {
            TableColumn c = dataJTable.getTableHeader().getColumnModel().getColumn(i);
            c.setHeaderValue(header[i]);
        }
        resultSet.close();
    }

}
