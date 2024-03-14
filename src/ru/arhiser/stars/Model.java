package ru.arhiser.stars;

import java.util.ArrayList;

public class Model {

    public static float INITIAL_Z_COORD = -3;   // z
    public static float MOTION_SPEED = 0.001f;  // скорость точки
    ArrayList<Point> points = new ArrayList<>();  // сохраняем коллекцию точек

    public void update(long elapsedTime){
        float birthChanse = 0.003f;        // вероятность появления новой точки

        if(random(0, 1) < birthChanse){    // Логика создания точки
            points.add(new Point(random(-1,1), random(-1,1), INITIAL_Z_COORD));
        }

        for (Point point:points){
            point.z += elapsedTime * MOTION_SPEED; // движение точек
        }

        points.removeIf(point -> point.z >= 0);  // удаление точки
    }

    private float random(float from,float to){    //случайные числа в нужном диапазоне
        return (float) (from + (to - from)* Math.random());
    }

    public ArrayList<Point> getPoints(){
        return points;
    }
}
