package Selenium_Framework.utils;

import Selenium_Framework.base.BaseDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.function.Function;

/**
 * A utility class for handling explicit, implicit, and fluent waits in Selenium.
 * This class provides a centralized way to manage different types of waits,
 * ensuring synchronization between the test script and the web browser by using
 * By locators to find elements.
 */
public class WaitUtils {

    // ======================
    // ✅ VISIBILITY
    // ======================

    /**
     * Waits for an element to be visible on the page using its locator.
     * This method is useful for ensuring an element is not only present in the DOM
     * but is also displayed to the user.
     *
     * @param locator The {@link By} locator used to find the element.
     * @param timeoutSeconds The maximum time in seconds to wait for the element's visibility.
     * @return The {@link WebElement} once it is visible.
     * @throws TimeoutException if the element is not visible within the specified time.
     */
    public static WebElement waitForVisibility(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // ======================
    // ✅ CLICKABILITY
    // ======================

    /**
     * Waits for an element to be clickable, meaning it is visible and enabled.
     * This is the recommended wait for any action that involves clicking on an element.
     *
     * @param locator The {@link By} locator used to find the element.
     * @param timeoutSeconds The maximum time in seconds to wait for clickability.
     * @return The {@link WebElement} once it is clickable.
     * @throws TimeoutException if the element is not clickable within the specified time.
     */
    public static WebElement waitForClickability(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // ======================
    // ✅ PRESENCE
    // ======================

    /**
     * Waits for an element to be present in the Document Object Model (DOM).
     * This does not guarantee that the element is visible.
     *
     * @param locator The {@link By} locator used to find the element.
     * @param timeoutSeconds The maximum time in seconds to wait for the element's presence.
     * @return The {@link WebElement} once it is present in the DOM.
     * @throws TimeoutException if the element is not present within the specified time.
     */
    public static WebElement waitForPresence(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // ======================
    // ✅ TEXT
    // ======================

    /**
     * Waits for a specific text string to be present within an element.
     *
     * @param locator The {@link By} locator used to find the element.
     * @param text The text string expected to be in the element.
     * @param timeoutSeconds The maximum time in seconds to wait for the text.
     * @return {@code true} if the text is found within the specified time, otherwise throws {@link TimeoutException}.
     */
    public static boolean waitForTextToBePresent(By locator, String text, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    // ======================
    // ✅ INVISIBILITY
    // ======================

    /**
     * Waits for an element to become invisible or not present in the DOM.
     * This is useful for waiting for loading spinners or pop-ups to disappear.
     *
     * @param locator The {@link By} locator used to find the element.
     * @param timeoutSeconds The maximum time in seconds to wait.
     * @return {@code true} if the element becomes invisible, otherwise throws {@link TimeoutException}.
     */
    public static boolean waitForInvisibility(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // ======================
    // ✅ FLUENT WAIT
    // ======================

    /**
     * Implements a Fluent Wait for an element, allowing for a custom polling interval.
     * This wait repeatedly checks for the element's presence, ignoring specified exceptions.
     *
     * @param locator The {@link By} locator for the element.
     * @param timeoutSeconds The maximum time to wait for the element.
     * @param pollingSeconds The interval in seconds to poll for the element.
     * @return The {@link WebElement} once it is found.
     * @throws TimeoutException if the element is not found within the specified time.
     */
    public static WebElement fluentWait(By locator, int timeoutSeconds, int pollingSeconds) {
        FluentWait<WebDriver> wait = new FluentWait<>(BaseDriver.getDriver())
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofSeconds(pollingSeconds))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }

    // ======================
    // ✅ IMPLICIT WAIT (use cautiously)
    // ======================

    /**
     * Sets a global Implicit Wait for the entire session.
     * This wait is applied to every element lookup. It's generally
     * recommended to use explicit waits instead to avoid unpredictable delays.
     *
     * @param timeoutSeconds The time in seconds to wait for an element to be found.
     */
    public static void setImplicitWait(int timeoutSeconds) {
        BaseDriver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutSeconds));
    }
}
