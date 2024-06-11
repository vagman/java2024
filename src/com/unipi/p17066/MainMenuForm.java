package com.unipi.p17066;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public MainMenuForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 700));
        setContentPane(mainMenuPanel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Attica Zoological Park - Main Menu");
        ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(img.getImage());

        showAllAvailableAnimalsButton.addActionListener(e -> {

        });

        addNewAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        searchAnimalByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        searchAnimalByIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        editAnimalByIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        deleteAnAnimalByButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        exitApplicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuForm.this.dispose();
            }
        });
    }

    public static void main(String[] args) {
        new MainMenuForm();
    }
    

}
