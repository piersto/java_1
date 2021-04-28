package ru.stqa.pft.soap2;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

    @Test

    public void testMyIp() {
        new GeoIPService().getGeoIPServiceSoap12().getGeoIp("45.42.102.219");
    }
}
