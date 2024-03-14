package ru.arhiser.stars;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {

    volatile static boolean isFrameReadyToDraw = true;

    public static void main(String[] args) {

        final int width = 1200;
        final int heigh = 800;

        JFrame frame = new JFrame();     // параметры окна
        frame.setSize(width, heigh);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BufferedImage image = new BufferedImage(width, heigh,
                BufferedImage.TYPE_INT_ARGB);    // изображение кадра

        ImageIcon imageIcon = new ImageIcon(image); // контрол для показа картинки
        JLabel picLable = new JLabel(imageIcon);

        BorderLayout borderLayout = new BorderLayout();
        frame.getContentPane().setLayout(borderLayout);
        frame.getContentPane().add(picLable, BorderLayout.CENTER);

        frame.setVisible(true);   //для показа окна


        // ОСНОВНОЙ ЦИКЛ

        Model model = new Model();// создане компонентов
        Render render = new Render();

        long lastTime = System.currentTimeMillis(); // замер времени

        while (frame.isVisible()) {
            long time = System.currentTimeMillis();
            model.update(time - lastTime);
            lastTime = time;

            if (isFrameReadyToDraw) { // синхронизация потока
                isFrameReadyToDraw = false;
                render.draw(image, model);
                SwingUtilities.invokeLater(() -> {
                    frame.repaint();
                    isFrameReadyToDraw = true;
                });


            }
        }
    }
}

