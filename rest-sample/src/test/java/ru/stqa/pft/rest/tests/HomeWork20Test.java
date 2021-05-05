package ru.stqa.pft.rest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class HomeWork20Test extends TestBase{

    @BeforeMethod
    public void skipIfNotFixed() throws MalformedURLException, ServiceException, RemoteException {
        skipIfNotFixed(1048);
    }

    @Test
    public void testCreateIssue() throws IOException {
        System.out.println("Bug is fixed. За работу!");
    }

}
