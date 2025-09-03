package tests;


import Selenium_Framework.base.BaseDriver;
import Selenium_Framework.utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.qameta.allure.*;
import java.awt.AWTException;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import org.testng.Assert;

import static org.testng.Assert.*;
import org.testng.asserts.SoftAssert;

public class HerokuAppTests {
    
    By TC1_textLocator = By.id("content"); 
    By TC2_AddLocator = By.cssSelector("button[onclick='addElement()']");
    By TC2_RemoveLocator = By.cssSelector("button.added-manually");
    By TC3_successAuth = By.id("content");
    By TC4_BrokenImagesLocator = By.tagName("img");
    By TC6_firstBoxLocator = By.xpath("//*[@id=\"checkboxes\"]/input[1]");
    By TC6_secondBoxLocator = By.xpath("//*[@id=\"checkboxes\"]/input[2]");
    By TC7_contextMneuLocator = By.id("hot-spot");
    By TC8_HomeLocator = By.xpath("//*[@id=\"content\"]/div/ul/li[1]/a");
    By TC8_AboutLocator = By.xpath("//*[@id=\"content\"]/div/ul/li[2]/a");
    By TC8_ContactUsLocator = By.xpath("//*[@id=\"content\"]/div/ul/li[3]/a");
    By TC8_PortfolioLocator = By.xpath("//*[@id=\"content\"]/div/ul/li[4]/a");
    By TC9_srcALocator = By.id("column-a");
    By TC9_destBLocator = By.id("column-b");
    By TC10_DropDownLocator = By.id("dropdown");
    By TC11_DynamicText1 = By.xpath("//*[@id=\"content\"]/div[1]/div[2]");
    By TC11_DynamicText2 = By.xpath("//*[@id=\"content\"]/div[2]/div[2]");
    By TC11_DynamicText3 = By.xpath("//*[@id=\"content\"]/div[3]/div[2]");
    By TC12_REMOVE_BUTTON = By.xpath("//button[text()='Remove']");
    By TC12_ADD_BUTTON = By.xpath("//button[text()='Add']");
    By TC12_MESSAGE = By.id("message");
    By TC13_ActionButtonLocator = By.xpath("//*[@id=\"input-example\"]/button");
    By TC13_displayedMSG = By.id("message");
    By TC14_dynamicLodingStart1 = By.xpath("//*[@id=\"start\"]/button"); 
    By TC14_dynamicLodingStart2 = By.xpath("//*[@id=\"start\"]/button");     
    By TC14_dynamicLodingmsg1 = By.id("finish");
    By TC14_dynamicLodingmsg2 = By.id("finish");  

    @BeforeClass
    public void setUpClass() {
        System.out.println("The test class is initialized");
    }

    @AfterClass
    public void tearDownClass() {
        System.out.println("The test class is ended");
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        BaseDriver.initializeDriver("chrome");
        System.out.println("the method is started");
    }

    @AfterMethod
    public void tearDownMethod(ITestResult result) throws Exception {
        // Attach screenshot on failure
        if (result.getStatus() == ITestResult.FAILURE) {
            byte[] screenshot = ((TakesScreenshot) BaseDriver.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(screenshot));
        }
        BaseDriver.quitDriver();
        System.out.println("the method is finished");
        }

    @Test
    public void TC1_AB_Test_Control() {
        BrowserUtils.navigateToURL("https://the-internet.herokuapp.com/abtest");
        String pageText = ElementUtils.getText(TC1_textLocator);
        System.out.println("The content of page1:\n" + pageText);
        Assert.assertTrue(pageText.contains("Test"), 
                "Page text does not contain expected string 'Test Control'. Actual text: " + pageText);
    }

    @Test
    public void TC2_Add_Remove_Elements() {
        BrowserUtils.navigateToURL("https://the-internet.herokuapp.com/add_remove_elements/");
        ElementUtils.click(TC2_AddLocator);
        System.out.println("The element is added successfully");
        ElementUtils.click(TC2_RemoveLocator);
        System.out.println("The element is removed successfully");
    }

    
    @Test
    public void TC3_BasicAuth() throws AWTException {
        BrowserUtils.navigateToURL("https://the-internet.herokuapp.com/basic_auth");
        KeyboardUtils.basicAuthUsingRobot("admin", "admin");
        // Verify page content
        String pageText = ElementUtils.getText(TC3_successAuth);
        System.out.println("Page content: " + pageText);
        assertTrue(pageText.contains("Congratulations!"));
    }

/*   
    @Test
    public void TC4_brokenImages() throws IOException {
        BrowserUtils.navigateToURL("https://the-internet.herokuapp.com/broken_images");

        List<WebElement> images = ElementUtils.getElements(TC4_BrokenImagesLocator);
        boolean brokenFound = false;

        for (WebElement img : images) {
          String src = img.getAttribute("src");

          HttpURLConnection connection = (HttpURLConnection) new URL(src).openConnection();
          connection.setRequestMethod("HEAD");
          connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode >= 400) {
            brokenFound = true;
            System.out.println("Broken image: " + src);
        } else {
            System.out.println("Valid image: " + src);
           }

           connection.disconnect(); // always disconnect
        }
     // assertFalse(brokenFound, "Some images are broken on the page!");
    }
*/
    
    @Test
    public void TC5_ChallengingDOM() {
        BrowserUtils.navigateToURL("https://the-internet.herokuapp.com/challenging_dom");
        for (int row = 1; row <= 10; row++) {
            for (int col = 1; col <= 6; col++) {
               String TC5_ElementLocators = String.format("//*[@id='content']/div/div/div/div[2]/table/tbody/tr[%d]/td[%d]", row, col);
               String cellText = ElementUtils.getText(By.xpath(TC5_ElementLocators));
               System.out.println("Row " + row + " Col " + col + ": " + cellText);
           }
       }
    } 

    
    @Test
    public void TC6_checkedBoxes() {
       BrowserUtils.navigateToURL("https://the-internet.herokuapp.com/checkboxes");

       ElementUtils.checkCheckbox(TC6_firstBoxLocator);
       System.out.println("The first checkbox is checked now");

       ElementUtils.uncheckCheckbox(TC6_secondBoxLocator);
       System.out.println("The second checkbox is unchecked now");
    }
    
    @Test
    public void TC7_contextMneu() {
       String expectedText = "You selected a context menu";
       BrowserUtils.navigateToURL("https://the-internet.herokuapp.com/context_menu");

       ActionsUtils.rightClick(TC7_contextMneuLocator);
       WaitUtils.setImplicitWait(2);
       String alerttext = AlertUtils.getAlertText();
            System.out.println("the alert text is : " + alerttext);
       AlertUtils.acceptAlert();
       assertTrue(alerttext.contains(expectedText));
    }
    
    @Test
    public void TC8_DisappearingElements()
    {   
        String ExpectedHomeLink = "https://the-internet.herokuapp.com/";
        String ExpectedAboutLink = "https://the-internet.herokuapp.com/about/";
        String ExpectedContactUsLink = "https://the-internet.herokuapp.com/contact-us/";
        String ExpectedPortfolioLink = "https://the-internet.herokuapp.com/portfolio/";
                
        BrowserUtils.navigateToURL("https://the-internet.herokuapp.com/disappearing_elements");
        ElementUtils.click(TC8_HomeLocator);
        assertEquals(BrowserUtils.GetCurrentLink() , ExpectedHomeLink);
        System.out.println("HOME IS DONE");
        
        BrowserUtils.navigateToURL("https://the-internet.herokuapp.com/disappearing_elements");
        ElementUtils.click(TC8_AboutLocator);
        assertEquals(BrowserUtils.GetCurrentLink() , ExpectedAboutLink);
        System.out.println("ABOUT IS DONE");
        
        BrowserUtils.navigateToURL("https://the-internet.herokuapp.com/disappearing_elements");
        ElementUtils.click(TC8_ContactUsLocator);
        assertEquals(BrowserUtils.GetCurrentLink() , ExpectedContactUsLink);
        System.out.println("CONTACT IS DONE");
        
        BrowserUtils.navigateToURL("https://the-internet.herokuapp.com/disappearing_elements");
        ElementUtils.click(TC8_PortfolioLocator);
        assertEquals(BrowserUtils.GetCurrentLink() , ExpectedPortfolioLink);        
        System.out.println("PORTFOLIO IS DONE");
    }
    
    @Test
    public void TC9_DragAndDrop() {
       BrowserUtils.navigateToURL("https://the-internet.herokuapp.com/drag_and_drop");
       ActionsUtils.dragAndDrop(TC9_srcALocator, TC9_destBLocator);  
    }
    
    @Test
    public void TC10_DropDown() {
       BrowserUtils.navigateToURL("https://the-internet.herokuapp.com/dropdown");
       ElementUtils.selectDropdownByIndex(TC10_DropDownLocator, 2);
        System.out.println("Option 1 is selected");
    }
    
    @Test
    public void TC11_DynamicContent()
    {
        BrowserUtils.navigateToURL("https://the-internet.herokuapp.com/dynamic_content");
        System.out.println("the content 1 : " + ElementUtils.getText(TC11_DynamicText1));
        System.out.println("the content 2 : " + ElementUtils.getText(TC11_DynamicText2));
        System.out.println("the content 3 : " + ElementUtils.getText(TC11_DynamicText3));    
    }
    
    
    @Test
    public void TC12_dynamicAddAndRemove() {
        BrowserUtils.navigateToURL("https://the-internet.herokuapp.com/dynamic_controls");

        ElementUtils.click(TC12_REMOVE_BUTTON);

        String removeMsg = WaitUtils.waitForVisibility(TC12_MESSAGE, 5).getText();
        System.out.println("Remove Message: " + removeMsg);
        assertTrue(removeMsg.contains("It's gone!"),
                "Expected 'It's gone!' but got: " + removeMsg);

        ElementUtils.click(TC12_ADD_BUTTON);

        String addMsg = WaitUtils.waitForVisibility(TC12_MESSAGE, 5).getText();
        System.out.println("Add Message: " + addMsg);
        assertTrue(addMsg.contains("It's back!"),
                "Expected 'It's back!' but got: " + addMsg);
    }
    
    @Test
    public void TC13_DynamicEnableAndDisable ()
    {
        BrowserUtils.navigateToURL("https://the-internet.herokuapp.com/dynamic_controls");
        
        ElementUtils.click(TC13_ActionButtonLocator);
        String enableMSG = WaitUtils.waitForVisibility(TC13_displayedMSG, 5).getText();
        System.out.println("the enabled MSG is " + enableMSG);
        assertTrue(enableMSG.contains("It's enabled!") , "Expected 'It's enabled!' but got" + enableMSG);
        
                
        ElementUtils.click(TC13_ActionButtonLocator);
        String disableMSG = WaitUtils.waitForVisibility(TC13_displayedMSG, 5).getText();
        System.out.println("the disabled MSG is " + disableMSG);
        assertTrue(disableMSG.contains("It's disabled!") , "Expected 'It's disabled!' but got " + disableMSG);      
    }
    
    @Test
    public void TC14_DynamicLoading1()
    {
        BrowserUtils.navigateToURL("https://the-internet.herokuapp.com/dynamic_loading/1");

        ElementUtils.click(TC14_dynamicLodingStart1);
        String msg = WaitUtils.waitForVisibility(TC14_dynamicLodingmsg1, 5).getText();
        System.out.println("MSG is " + msg);
        assertTrue(msg.contains("Hello World!") , "Expected 'Hello World!' but got" + msg);        
    }
    
    @Test
    public void TC15_DynamicLoading2()
    {
        BrowserUtils.navigateToURL("https://the-internet.herokuapp.com/dynamic_loading/2");

        ElementUtils.click(TC14_dynamicLodingStart2);
        String msg = WaitUtils.waitForVisibility(TC14_dynamicLodingmsg2, 5).getText();
        System.out.println("MSG is " + msg);
        assertTrue(msg.contains("Hello World!") , "Expected 'Hello World!' but got" + msg);        
    }
   
/*
    mvn clean test
     mvn allure:report => for the first time 
    allure open target/allure-report

    */
            
 }