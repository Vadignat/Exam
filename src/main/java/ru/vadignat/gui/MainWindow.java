package ru.vadignat.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final Dimension minSz = new Dimension(600, 400);
    private static final int  std = 8;
    public MainWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(minSz);

        GroupLayout gl = new GroupLayout(getContentPane());
        setLayout(gl);


    }
}
