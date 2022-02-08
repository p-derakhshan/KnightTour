package com.der.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {

    public static int[][] FinalMatrix;
    public static int level;
    int BoardSize;
    int cellSize;
    int start = 100;
    public static int x0, y0;

    public void paint(Graphics g) { //WIDTH=400

        BoardSize = FinalMatrix.length;
        cellSize = 400 / BoardSize;
        g.setColor(Color.CYAN);
        g.fillRect(start - 10, start - 10, 420, 420);
        int iterator = 0;
        int x, y;
        for (int i = 0; i < BoardSize; i++) {
            x = i * cellSize + start;
            for (int j = 0; j < BoardSize; j++) {
                y = j * cellSize + start;
                if ((j % 2 == 0 && i % 2 == 0) || (j % 2 != 0 && i % 2 != 0)) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(y, x, cellSize, cellSize);
            }
        }

        color(g);
    }

    public void color(Graphics g) {
        int N = BoardSize;
        int x = y0;
        int y = x0;
        int gap = (cellSize / 2) + start;
        int count = 0;
        while (count < level) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (FinalMatrix[i][j] == count) {
                        x = j;
                        y = i;
                    }
                }
            }
            g.setColor(Color.RED);
            g.drawString(String.valueOf(count), (x * cellSize) + gap - 5, (y * cellSize) + gap + 5);

            count++;
        }
        if (count != 0) {
            String out = String.valueOf(count - 1);

//            g.setColor(Color.GRAY);
//            g.fillRect((x * cellSize) + 102, (y * cellSize) + 102, cellSize - 4, cellSize - 4);

            Image img = Toolkit.getDefaultToolkit().getImage("src/com/der/graphics/knight.png");
            g.drawImage(img, (x * cellSize) + start + 3, (y * cellSize) + start+2, cellSize - 8, cellSize-4, null, this);
        }
    }

}
