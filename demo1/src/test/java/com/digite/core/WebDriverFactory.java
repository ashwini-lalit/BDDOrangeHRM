package com.digite.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.io.FileInputStream;
import java.util.Properties;
import java.time.Duration;

public class WebDriverFactory {
    private static Properties config;

    static {
        config = new Properties();
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            config.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WebDriver createDriver() {
        WebDriver driver;
        String browser = config.getProperty("browser");
        //String browser = config.getProperty("browser", "chrome");
        boolean headless = Boolean.parseBoolean(config.getProperty("headless", "false"));


        switch (browser.toLowerCase()) {
            case "chrome":
//                System.setProperty("webdriver.chrome.driver", "E:\\Dell_Demo\\demo1\\src\\test\\resources\\drivers\\chromedriver.exe");
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless");
                }
                chromeOptions.addArguments("--start-maximized");
              //  chromeOptions.addArguments("--remote-allow-origins=*");

                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }

                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new IllegalArgumentException("Browser type not supported: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(Long.parseLong(config.getProperty("implicitWait", "10")))
        );

        return driver;
    }
}