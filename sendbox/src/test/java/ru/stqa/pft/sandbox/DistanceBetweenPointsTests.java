package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceBetweenPointsTests {

    @Test
    public void testDistance(){
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 0);
        Assert.assertEquals(p1.distance(p2), 0);
    }

    @Test
    public void testDistance1(){
        Point p1 = new Point(4, 4);
        Point p2 = new Point(1, 1);
        Assert.assertEquals(p1.distance(p2), 4.242640687119285);
    }

    @Test
    public void testDistance2(){
        Point p1 = new Point(-4, -4);
        Point p2 = new Point(-1, -1);
        Assert.assertEquals(p1.distance(p2), 4.242640687119285);
    }

    @Test
    public void testDistance3(){
        Point p1 = new Point(0, 1);
        Point p2 = new Point(0, 1);
        Assert.assertEquals(p1.distance(p2), 0);
    }

    @Test
    public void testDistance4(){
        Point p1 = new Point(-1, 0);
        Point p2 = new Point(1, 0);
        Assert.assertEquals(p1.distance(p2), 2);
    }
}


