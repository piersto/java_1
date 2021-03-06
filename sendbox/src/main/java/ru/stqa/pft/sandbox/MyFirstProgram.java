package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Площадь прямоугольника со стороной " + r.a + " и " + r.b + " = " + r.area());

        Point p1 = new Point(-4, 10);
        Point p2 = new Point(0, 13);
        double d = p1.distance(p2);
        System.out.println("Distance equal: "  + d);

        double x1 = 0;
        double y1 = -0;
        double x2 = -3;
        double y2 = 4;
        System.out.println("Distance equal: " + distance0(x1, x2, y1, y2));


        Point p11 = new Point(11, 5);
        Point p22 = new Point(12, 5);
        System.out.println("Distance equal: " + distance2(p11, p22));
    }



    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "! РАЗ два три! € ");
    }

    public static double distance2(Point p1, Point p2) {
        return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
    }

    public static double distance0(double x1, double x2, double y1, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }
}