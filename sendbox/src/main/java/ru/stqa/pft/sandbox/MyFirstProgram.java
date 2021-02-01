package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l  + " = " + s.area());

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Площадь прямоугольника со стороной " + r.a + " и " + r.b + " = " + r.area());

        double x1=0;
        double y1=-0;
        double x2=-3;
        double y2=4;
        System.out.println("Distance equal: " + distance0(x1, x2, y1, y2));


        Point a = new Point();
        Point b = new Point();
        a.x=11;
        a.y=5;
        b.x=12;
        b.y=5;
        System.out.println("Distance equal: " + distance(a, b));

    }

    public static void hello(String somebody){
        System.out.println("Hello, " + somebody + "! РАЗ два три! € ");
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
    }

    public static double distance0(double x1, double x2, double y1, double y2){
        return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
    }
}