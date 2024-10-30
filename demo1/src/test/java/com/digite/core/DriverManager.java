package com.digite.core;

import org.openqa.selenium.WebDriver;

/**
 * The DriverManager class is responsible for managing the WebDriver instances
 * using a ThreadLocal to ensure thread safety. It provides methods to get, set,
 * and remove the WebDriver instance for the current thread.
 */
public class DriverManager
{
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Retrieves the WebDriver instance associated with the current thread.
     *
     * @return the WebDriver instance for the current thread, or null if no instance is set
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Sets the WebDriver instance for the current thread.
     *
     * @param webDriver the WebDriver instance to be associated with the current thread
     */
    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    /**
     * Removes the WebDriver instance associated with the current thread.
     */
    public static void removeDriver() {
        driver.remove();
    }
}



