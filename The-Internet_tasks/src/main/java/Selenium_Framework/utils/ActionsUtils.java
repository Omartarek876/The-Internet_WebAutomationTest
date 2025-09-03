package Selenium_Framework.utils;

import Selenium_Framework.base.BaseDriver;
import static Selenium_Framework.utils.ElementUtils.getElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

/**
 * A utility class for performing advanced user interactions using Selenium's
 * {@link Actions} class. This class provides a set of helper methods to
 * simplify common actions like hovering, drag-and-drop, double-clicking,
 * right-clicking, and complex keyboard inputs.
 */
public class ActionsUtils {

    /**
     * Performs a hover action over a specified element.
     * This simulates a mouse-over event, which can be useful for triggering
     * hidden menus or tooltips.
     *
     * @param locator The {@link By} locator of the element to hover over.
     */
    public static void hoverOverElement(By locator) {
        Actions actions = new Actions(BaseDriver.getDriver());
        actions.moveToElement(getElement(locator)).perform();
    }

    /**
     * Performs a drag-and-drop operation from a source element to a target element.
     *
     * @param sourceLocator The {@link By} locator of the element to drag.
     * @param targetLocator The {@link By} locator of the element to drop into.
     */
    public static void dragAndDrop(By sourceLocator, By targetLocator) {
        Actions actions = new Actions(BaseDriver.getDriver());
        actions.dragAndDrop(getElement(sourceLocator), getElement(targetLocator)).perform();
    }

    /**
     * Performs a double-click action on the specified element.
     *
     * @param locator The {@link By} locator of the element to double-click.
     */
    public static void doubleClick(By locator) {
        Actions actions = new Actions(BaseDriver.getDriver());
        actions.doubleClick(getElement(locator)).perform();
    }

    /**
     * Performs a right-click (context-click) action on the specified element.
     * This is useful for testing context menus that appear on a right-click.
     *
     * @param locator The {@link By} locator of the element to right-click.
     */
    public static void rightClick(By locator) {
        Actions actions = new Actions(BaseDriver.getDriver());
        actions.contextClick(getElement(locator)).perform();
    }

    /**
     * Scrolls the browser window to bring the specified element into view.
     *
     * @param locator The {@link By} locator of the element to scroll to.
     */
    public static void scrollToElement(By locator) {
        Actions actions = new Actions(BaseDriver.getDriver());
        actions.scrollToElement(getElement(locator)).perform();
    }

    /**
     * Sends a combination of keys (a chord) to the active element on the page.
     * This is useful for keyboard shortcuts like Ctrl+C or Ctrl+V.
     *
     * @param key1 The first key to press down.
     * @param key2 The second key to send while the first is held down.
     */
    public static void sendKeyCombination(Keys key1, Keys key2) {
        Actions actions = new Actions(BaseDriver.getDriver());
        actions.keyDown(key1).sendKeys(key2).keyUp(key1).perform();
    }

    /**
     * Performs a combined hover and click action on the specified element.
     * This is useful for scenarios where a simple click does not work and requires
     * a prior mouse-over action.
     *
     * @param locator The {@link By} locator of the element to hover over and click.
     */
    public static void hoverAndClick(By locator) {
        Actions actions = new Actions(BaseDriver.getDriver());
        actions.moveToElement(getElement(locator)).click().perform();
    }

    /**
     * Clicks and holds the mouse button down on the specified element.
     * This is often the first step in a drag-and-drop sequence.
     *
     * @param locator The {@link By} locator of the element to click and hold.
     */
    public static void clickAndHold(By locator) {
        Actions actions = new Actions(BaseDriver.getDriver());
        actions.clickAndHold(getElement(locator)).perform();
    }

    /**
     * Releases the mouse button at the current location.
     * This is used to complete a drag operation that started with `clickAndHold`.
     */
    public static void releaseMouse() {
        Actions actions = new Actions(BaseDriver.getDriver());
        actions.release().perform();
    }
}
