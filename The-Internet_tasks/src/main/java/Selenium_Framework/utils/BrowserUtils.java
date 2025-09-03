package Selenium_Framework.utils;

import Selenium_Framework.base.BaseDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class providing helper methods for browser actions,
 * tab/window management, and navigation. This class simplifies common
 * browser-level operations.
 */
public class BrowserUtils {

    /**
     * Switches to a specific browser tab using its index.
     * The tab indices are zero-based.
     *
     * @param index The index of the tab to switch to (e.g., 0 for the first tab).
     * @throws IllegalArgumentException if the provided index is out of the valid range of tabs.
     */
    public static void switchToTab(int index) {
        List<String> tabs = new ArrayList<>(BaseDriver.getDriver().getWindowHandles());
        if (index >= 0 && index < tabs.size()) {
            BaseDriver.getDriver().switchTo().window(tabs.get(index));
        } else {
            throw new IllegalArgumentException("Invalid tab index: " + index);
        }
    }
    
    /**
     * Gets the URL of the current browser page.
     *
     * @return The URL of the current page as a {@link String}.
     */
    public static String GetCurrentLink() {
        return BaseDriver.getDriver().getCurrentUrl();
    }

    /**
     * Closes the current browser tab and switches the WebDriver focus back to the first tab.
     */
    public static void closeCurrentTabAndSwitchToFirst() {
        BaseDriver.getDriver().close();
        List<String> tabs = new ArrayList<>(BaseDriver.getDriver().getWindowHandles());
        BaseDriver.getDriver().switchTo().window(tabs.get(0));
    }

    /**
     * Refreshes the current browser page.
     */
    public static void refreshPage() {
        BaseDriver.getDriver().navigate().refresh();
    }

    /**
     * Navigates to a specified URL. This is equivalent to typing a URL into the address bar.
     *
     * @param url The URL to navigate to.
     */
    public static void navigateToURL(String url) {
        BaseDriver.getDriver().navigate().to(url);
    }

    /**
     * Navigates back to the previous page in the browser's history.
     */
    public static void navigateBack() {
        BaseDriver.getDriver().navigate().back();
    }

    /**
     * Navigates forward to the next page in the browser's history.
     */
    public static void navigateForward() {
        BaseDriver.getDriver().navigate().forward();
    }
}