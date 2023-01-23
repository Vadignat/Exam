package ru.vadignat.gui;

import ru.vadignat.Converter;
import ru.vadignat.GraphicsPanel;
import ru.vadignat.Painter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        FunctionPainter fp1 = new FunctionPainter() {
            @Override
            public double invoke(double x) {
                return 2*x/(1-Math.pow(x,2));
            }
        };
        FunctionPainter fp2 = new FunctionPainter(Color.BLUE) {
            @Override
            public double invoke(double x) {
                return 0;
            }
            @Override
            public void paint(Graphics g, int width, int height){
                if(cvrt == null) {
                    cvrt = new Converter(-5.0,5.0,-5.0,5.0,width,height);
                }
                cvrt = new Converter(cvrt.getxMin(), cvrt.getxMax(), cvrt.getyMin(), cvrt.getyMax(), width, height);
                g.setColor(color);
                if(show) {
                    PointPainter pp = new PointPainter(color);
                    pp.changeConverter(cvrt);
                    pp.changeRadius(1);
                    double delta = 0.0001;
                    double t = 0.;
                    pp.addPoint(x(t), y(t));
                    for(t = t + delta; t < 2 * Math.PI; t += delta)
                    {
                        pp.addPoint(x(t), y(t));
                        g.drawLine(cvrt.xCrt2Scr(x(t - delta)), cvrt.yCrt2Scr(y(t - delta)), cvrt.xCrt2Scr(x(t)), cvrt.yCrt2Scr(y(t)));
                    }
                }
            }

            private double x(double t){
                return Math.cos(t) * (Math.cos(t) + 2);
            }

            private double y(double t){
                return Math.sin(t) * (Math.cos(t) + 2);
            }

        };
        fp2.show = false;

        painters.add(fp1);
        painters.add(fp2);
        GraphicsPanel mainPanel = new GraphicsPanel(painters);
        mainPanel.setBackground(Color.WHITE);

        JRadioButton firstButton = new JRadioButton("1", true);
        JRadioButton secondButton = new JRadioButton("2");

        firstButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                fp1.show = !fp1.show;
                mainPanel.repaint();
            }
        });

        secondButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){

                fp2.show = !fp2.show;
                mainPanel.repaint();
            }
        });

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
