package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

@Test
public class SoapTests_3 {
    public void testGetIssueStatus() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = new MantisConnectLocator()
                .getMantisConnectPort(new URL
                        ("http://localhost/mantisbt-2.25.0/api/soap/mantisconnect.php"));

        BigInteger b = new BigInteger("0000004");
        IssueData issueData = mc.mc_issue_get("administrator", "root", b);
        System.out.println(issueData.getStatus().getName());
        System.out.println(issueData.getDescription());
    }
}
