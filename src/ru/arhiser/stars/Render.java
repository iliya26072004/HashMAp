package ru.arhiser.stars;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Render {

    public void draw(BufferedImage image, Model model){  // черный экран
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setPaint(new Color(0,0,0));
        graphics2D.fillRect(0,0,image.getWidth(),image.getHeight());

        for (Point point: model.getPoints()){     // рисование точек
            int sx = image.getWidth() / 2 + (int) (image.getWidth() / 2 * point.x / point.z);
            int sy = image.getHeight() / 2 + (int) (image.getHeight() / 2 * point.y / point.z);
            if(sx < image.getWidth() && sx > 0 && sy < image.getHeight() && sy > 0){
                int color = 255 + (int) (point.z * (255 / Math.abs(Model.INITIAL_Z_COORD)));
                image.setRGB(sx,sy,0xFFFFFFFF| color << 16 | color << 8 | color);// угосание точек
            }

        }
    }

}