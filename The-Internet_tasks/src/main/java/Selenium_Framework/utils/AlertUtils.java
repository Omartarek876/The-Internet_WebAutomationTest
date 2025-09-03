package Selenium_Framework.utils;

import Selenium_Framework.base.BaseDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;

/**
 * A utility class for handling JavaScript alerts, confirms, and prompts in Selenium.
 * This class provides a set of helper methods to manage common alert interactions
 * without having to manually switch to the alert context each time.
 */
public class AlertUtils {

    /**
     * Accepts the currently displayed alert, which is the equivalent of clicking "OK".
     */
    public static void acceptAlert() {
        Alert alert = BaseDriver.getDriver().switchTo().alert();
        alert.accept();
    }

    /**
     * Dismisses the currently displayed alert, which is the equivalent of clicking "Cancel".
     */
    public static void dismissAlert() {
        Alert alert = BaseDriver.getDriver().switchTo().alert();
        alert.dismiss();
    }

    /**
     * Retrieves the text from the currently displayed alert.
     *
     * @return The text content of the alert as a {@link String}.
     */
    public static String getAlertText() {
        Alert alert = BaseDriver.getDriver().switchTo().alert();
        return alert.getText();
    }

    /**
     * Sends text to a prompt alert and then accepts it.
     * This method is specifically for alerts that require user input.
     *
     * @param text The text to send to the alert's input field.
     */
    public static void sendTextToAlert(String text) {
        Alert alert = BaseDriver.getDriver().switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
    }
    
    /**
     * Sends a username and password to a basic authentication alert.
     * It sends the username, tabs to the password field, sends the password,
     * but does not automatically accept the alert. The user must call `acceptAlert()`
     * or `dismissAlert()` separately.
     *
     * @param name The username to enter into the authentication alert.
     * @param password The password to enter into the authentication alert.
     */
    public static void sendAuthToAlert(String name, String password) {
        Alert alert = BaseDriver.getDriver().switchTo().alert();
        alert.sendKeys(name + Keys.TAB + password);
    }
}