package ru.vadignat.gui;

import ru.vadignat.GraphicsPanel;
import ru.vadignat.Painter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {
    private final Dimension minSz = new Dimension(600, 400);
    private static final int  std = 8;
    public MainWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(minSz);

        GroupLayout gl = new GroupLayout(getContentPane());
        setLayout(gl);

        CartesianPainter crt = new CartesianPainter();
        ArrayList<ru.vadignat.Painter> painters = new ArrayList<Painter>();
        painters.add(crt);

        GraphicsPanel mainPanel = new GraphicsPanel(painters);
        mainPanel.setBackground(Color.WHITE);
        JRadioButton radioButton = new JRadioButton();

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(std)
                .addGroup(gl.createParallelGroup()
                        .addComponent(mainPanel)
                        .addComponent(radioButton)
                )
                .addGap(std)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(10)
                .addComponent(mainPanel)
                .addGap(7)
                .addComponent(radioButton)
                .addGap(10)
        );
    }
}
