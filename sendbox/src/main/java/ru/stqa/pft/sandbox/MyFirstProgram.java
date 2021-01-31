package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l  + " = " + s.area());

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Площадь прямоугольника со стороной " + r.a + " и " + r.b + " = " + r.area());

        double x1=1;
        double y1=2;
        double x2=3;
        double y2=4;
        System.out.println("Distance equal: " + distance(x1, x2, y1, y2));

    }

    public static void hello(String somebody){
        System.out.println("Hello, " + somebody + "! РАЗ два три! € ");
    }

    public static double distance(double x1, double x2, double y1, double y2){
        return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
    }
}