package com.firstpass;

import com.firstpass.services.PasswordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PasswordGeneratorTests{

    @Autowired
    PasswordService passwordService;

    @Test
    public void sanityTest() {
        String userSecret = "sampleUser";
        String appName = "appName";
        String one = passwordService.getPassword(userSecret, appName);
        String two = passwordService.getPassword(userSecret, appName);
        assertEquals(one, two);
    }

    @Test
    public void verifyNoUserSecret() {
        String appName = "google";
        String one = passwordService.getPassword(appName);
        String two = passwordService.getPassword("default", appName);
        assertEquals(one, two);
    }

    @Test
    public void verifyEmptyUserNameAndAppName() {
        String emptyResponse = passwordService.getPassword("", "");
        assertEquals(emptyResponse, "Invalid input");
    }

    @Test
    public void verifyEmptyUserName() {
        String emptyResponse = passwordService.getPassword("");
        assertEquals(emptyResponse, "Invalid input");
    }

    @Test
    public void verifylengthOneString() {
        String appName = "a";
        String actual = passwordService.getPassword(appName);
        String expected = "PPPP&6666$";
        assertEquals(expected, actual);
    }

    @Test
    public void verifySpecialCharacters() {
        String appName = "&^%#@!)(&";
        String value = passwordService.getPassword(appName);
        assertEquals("Injczlb&2654636$", value);
    }

}
