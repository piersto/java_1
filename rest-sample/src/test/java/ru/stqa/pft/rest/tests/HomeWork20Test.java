package ru.stqa.pft.rest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class HomeWork20Test extends TestBase{

    @BeforeMethod
    public void skipIfNotFixed() throws IOException {
        skipIfNotFixed(1048);
    }

    @Test
    public void testCreateIssue() throws IOException {
        System.out.println("Bug is fixed. За работу!");
    }

}
