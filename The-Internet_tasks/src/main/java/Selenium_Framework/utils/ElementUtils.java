package Selenium_Framework.utils;

import Selenium_Framework.base.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

/**
 * A utility class for common element interactions, providing a set of
 * helper methods to simplify and standardize operations like clicking,
 * typing, and selecting dropdowns using a By locator.
 */
public class ElementUtils {

    /**
     * Fetches a single {@link WebElement} after waiting for its visibility.
     * This method combines element location and explicit waiting to ensure the
     * element is ready for interaction.
     *
     * @param locator The {@link By} locator of the element.
     * @return The located {@link WebElement} object.
     */
    public static WebElement getElement(By locator) {
        return WaitUtils.waitForVisibility(locator, 10);
    }
    
    /**
     * Fetches a list of {@link WebElement} objects after waiting for their presence in the DOM.
     * This is useful for interacting with multiple elements that share the same locator.
     *
     * @param locator The {@link By} locator for the list of elements.
     * @return A {@link List} of {@link WebElement} objects.
     */
    public static List<WebElement> getElements(By locator) {
        WebDriverWait wait = new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    /**
     * Clicks on an element after locating it.
     *
     * @param locator The {@link By} locator of the element to click.
     */
    public static void click(By locator) {
        getElement(locator).click();
    }

    /**
     * Clears any existing text and then enters new text into an input field.
     * This method ensures the field is empty before entering the new value.
     *
     * @param locator The {@link By} locator of the input element.
     * @param text The new text to enter into the field.
     */
    public static void clearAndSendKeys(By locator, String text) {
        WebElement element = getElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Gets the text of a located element.
     *
     * @param locator The {@link By} locator of the element.
     * @return The text content of the element as a {@link String}.
     */
    public static String getText(By locator) {
        return getElement(locator).getText();
    }

    /**
     * Selects an option from a dropdown menu by its visible text.
     *
     * @param locator The {@link By} locator of the dropdown element (a `<select>` tag).
     * @param visibleText The visible text of the option to be selected.
     */
    public static void selectDropdownByVisibleText(By locator, String visibleText) {
        new Select(getElement(locator)).selectByVisibleText(visibleText);
    }

    /**
     * Selects an option from a dropdown menu by its `value` attribute.
     *
     * @param locator The {@link By} locator of the dropdown element.
     * @param value The value attribute of the option to be selected.
     */
    public static void selectDropdownByValue(By locator, String value) {
        new Select(getElement(locator)).selectByValue(value);
    }

    /**
     * Selects an option from a dropdown menu by its index.
     *
     * @param locator The {@link By} locator of the dropdown element.
     * @param index The zero-based index of the option to be selected.
     */
    public static void selectDropdownByIndex(By locator, int index) {
        new Select(getElement(locator)).selectByIndex(index);
    }

    /**
     * Checks a checkbox if it is not already selected.
     * This method first locates the checkbox and then clicks it only if it's currently unchecked.
     *
     * @param locator The {@link By} locator of the checkbox element.
     */
    public static void checkCheckbox(By locator) {
        WebElement checkbox = getElement(locator);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    /**
     * Unchecks a checkbox if it is currently selected.
     *
     * @param locator The {@link By} locator of the checkbox element.
     */
    public static void uncheckCheckbox(By locator) {
        WebElement checkbox = getElement(locator);
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }

    /**
     * Selects a radio button if it is not already selected.
     *
     * @param locator The {@link By} locator of the radio button element.
     */
    public static void selectRadioButton(By locator) {
        WebElement radioButton = getElement(locator);
        if (!radioButton.isSelected()) {
            radioButton.click();
        }
    }
}
