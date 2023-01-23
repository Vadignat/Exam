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

        FunctionPainter fp1 = new FunctionPainter(Color.black) {
            @Override
            public double invoke(double x) {
                return 2*x/(1-Math.pow(x,2));
            }
        };
        painters.add(fp1);
        GraphicsPanel mainPanel = new GraphicsPanel(painters);
        mainPanel.setBackground(Color.WHITE);

        JRadioButton firstButton = new JRadioButton("1", true);
        JRadioButton secondButton = new JRadioButton("2");

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(std)
                .addGroup(gl.createParallelGroup()
                        .addComponent(mainPanel)
                        .addComponent(firstButton)
                        .addComponent(secondButton)
                )
                .addGap(std)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(10)
                .addComponent(mainPanel)
                .addGap(std)
                .addComponent(firstButton)
                .addGap(std)
                .addComponent(secondButton)
                .addGap(10)
        );
    }
}
