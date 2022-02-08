/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.der.graphics;

import java.awt.Color;
import java.awt.Dimension;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.JFrame;

public class Frame extends JFrame {

    public Frame(int[][] matrix, int size, int x0, int y0) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.LIGHT_GRAY);
        setTitle("Knight's Tour");
        setResizable(false);
        int count = 0;
        while (count <= size) {
            BoardPanel cb = new BoardPanel();
            setSize(WIDTH, HEIGHT);
            cb.FinalMatrix = matrix;
            cb.level = count;
            BoardPanel.x0 = x0;
            BoardPanel.y0 = y0;
            add(cb);
            init();
            try {
                Thread.sleep(400);
            } catch (Exception ex) {
            }
            remove(cb);
            count++;
        }
    }

    public void init() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
