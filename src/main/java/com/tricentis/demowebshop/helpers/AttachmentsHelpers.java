package com.tricentis.demowebshop.helpers;

import io.qameta.allure.Attachment;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.tricentis.demowebshop.config.DevConfig.DEV_CONFIG;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.openqa.selenium.OutputType.BYTES;

public class AttachmentsHelpers {
    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] attachScreenshot(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(BYTES);
    }

    @Attachment(value = "Page source", type = "text/html")
    public static byte[] attachPageSource() {
        return getWebDriver().getPageSource().getBytes(UTF_8);
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String attachVideo() {
        return "<html>" +
                "<body>" +
                "<video width='100%' height='100%' controls autoplay><source src='"
                + getWebVideoUrl()
                + "' type='video/mp4'>" +
                "</video>" +
                "</body>" +
                "</html>";
    }

    private static String getWebVideoUrl() {
        try {
            return new URL("https://" + DEV_CONFIG.getSelenoidUrl() + "/video/" + getSessionId() + ".mp4") + "";
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString().replace("selenoid", "");
    }
}
