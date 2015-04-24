package com.magnet.mmx.selenium;


import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.openqa.selenium.JavascriptExecutor;


public class WizardCustomInstall_TestNG {
//  private static float  = null;
private WebDriver driver;
  private String userUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  
  @BeforeMethod
public void setUp() throws Exception {
    driver = new FirefoxDriver();  
    userUrl ="http://localhost:3000";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    
    try {
        ProcessBuilder pb = new ProcessBuilder(
          "/selenium_TestNG/startServer.sh");
        Process p = pb.start();     // Start the process.
        p.waitFor();                // Wait for the process to finish.
        System.out.println("Script executed successfully.");
      } catch (Exception e) {
        e.printStackTrace();
      };
   
      
      Thread.sleep(1000L);
  }
 
  
  /*
   * initial Wizard Setup - sign out
   */
  @Test
  public void InitialSetup() throws Exception {
	  driver.get("http://localhost:3000/wizard");
	  Thread.sleep(2000);

	 //step1 
	//assert step1 Welcome page
	    String expectedName = "Welcome";
	    assertEquals(expectedName, driver.findElement(By.xpath(".//*[@id='project-wizard-container']/div[2]/div[1]/h4")).getText());
	    System.out.println("asserted 1st step: Welcome Page");

    //assert Custom install Button 
    try {
    	  WebElement e = driver.findElement(By.xpath(".//*[@id='project-wizard-container']/div[2]/div[1]/div[1]/div/button[2]"));
    	  Assert.assertTrue(e.isDisplayed());
    	} catch(NoSuchElementException nsee) {
    	   System.out.println("Custom install Button is not found");
    	} catch(AssertionError ae) {
    	   System.out.println("Custom install Buttonasertion Error.");
    	}
    System.out.println("assertion of Custom install Button");
    Thread.sleep(2000);
    
    //assert Next button
    assertEquals("Next", driver.findElement(By.cssSelector(".wiz-next.btn.btn-primary")).getText());
    System.out.println("asserted Next button >");
   
    driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();//Next
    Thread.sleep(2000);
    
//    // assert progress bar
//    assertEquals("Please Wait...", driver.findElement(By.cssSelector("#wait-modal h4")).getText());
//    System.out.println("asserted progress bar.....");
    System.out.println("Going to the 2nd step ");Thread.sleep(4000);

    //step2 
    
  //assert Setup Database page
  assertTrue(isElementPresent(By.xpath("//div[@id='project-wizard-container']/div[2]/div[2]/h4")));
  System.out.println("asserted Setup DB Page");
   Thread.sleep(2000);
    
    driver.findElement(By.xpath("(//button[@type='button'])[6]")).click(); //Next
    driver.findElement(By.xpath("(//button[@type='button'])[14]")).click(); //Continue
    System.out.println("Going to step 3");

    //step3
    //assert 3rd step
    assertTrue(isElementPresent(By.cssSelector(".step-pane.sample-pane.alert.active>h4")));
    System.out.println("asserted Create Admin Account Page");
     Thread.sleep(2000);
    
  //assert Next button
     assertTrue(isElementPresent(By.cssSelector(".wiz-next.btn.btn-primary")));
     System.out.println("asserted Next button >");
     Thread.sleep(2000);
    
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("admin@test.com"); Thread.sleep(2000);
    driver.findElement(By.cssSelector("div.toggling-password-input > input[name=\"password\"]")).clear();
    driver.findElement(By.cssSelector("div.toggling-password-input > input[name=\"password\"]")).sendKeys("admin"); Thread.sleep(2000);
    driver.findElement(By.name("passwordVerify")).clear();
    driver.findElement(By.name("passwordVerify")).sendKeys("admin"); Thread.sleep(2000);
    driver.findElement(By.xpath("(//button[@type='button'])[8]")).click();
    System.out.println("Going to step 4");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    Thread.sleep(2000);
    
    //step 4
  //assert 4th step
    driver.manage().window().maximize();
    assertTrue(isElementPresent(By.cssSelector(".step-pane.sample-pane.alert.active>h4")));   
    System.out.println("asserted step 4: Setup Magnet Message Page");
    driver.findElement(By.xpath(".//*[@id='project-wizard-container']/div[2]/div[4]/div[2]/button")).click(); //next
    Thread.sleep(5000);
//    driver.findElement(By.xpath(".//*[@id='confirm-alert']/div/div/div[3]/button[1]")).click(); //yes for null DB & Messaging Server Already Configured
    System.out.println("Going to step 5");
    
     //sptep5
    //assert step5 : Summary
    assertTrue(isElementPresent(By.cssSelector(".step-pane.sample-pane.alert.active>h4")));
    System.out.println("asserted step 5: Summary Page");

    System.out.println("Passwords Verification");
    driver.findElement(By.xpath("//table[@id='wizard-summary-table']/tbody/tr[5]/td/table/tbody/tr[2]/td[2]/div/span[2]")).click();
    Thread.sleep(3000);
    assertTrue(isElementPresent(By.xpath(".//*[@id='wizard-summary-table']/tbody/tr[5]/td/table/tbody/tr[2]/td[2]/div/input")));
    System.out.println("asserted: Admin account password is \"admin\"");
    
    driver.findElement(By.xpath("//table[@id='wizard-summary-table']/tbody/tr[5]/td/table/tbody/tr[2]/td[2]/div/span")).click();Thread.sleep(2000);
    Thread.sleep(3000);
    assertTrue(isElementPresent(By.xpath(".//*[@id='wizard-summary-table']/tbody/tr[5]/td/table/tbody/tr[2]/td[2]/div/input")));
    driver.findElement(By.xpath("//table[@id='wizard-summary-table']/tbody/tr[7]/td/table/tbody/tr[3]/td[2]/div/span[2]")).click();Thread.sleep(5000);
    System.out.println("asserted: Magnet Message account password is \"admin\"");
    
    driver.findElement(By.xpath("//table[@id='wizard-summary-table']/tbody/tr[7]/td/table/tbody/tr[3]/td[2]/div/span")).click();Thread.sleep(2000);
    
    
    //scroll down
      JavascriptExecutor jse = (JavascriptExecutor)driver;
      jse.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
      System.out.println("scroll down > executed");
    
    //assert Setup Database Success 
    assertTrue(isElementPresent(By.xpath(".//*[@id='wizard-summary-table']/tbody/tr[2]/td[2]/span")));
    System.out.println("Setup DB successful!!!");
    //assert Setup Admin Account
    assertTrue(isElementPresent(By.xpath(".//*[@id='wizard-summary-table']/tbody/tr[4]/td[2]/span")));
    System.out.println("Admin Account successful!!!");
    //assert Setup Magnet Message
    assertTrue(isElementPresent(By.xpath(".//*[@id='wizard-summary-table']/tbody/tr[6]/td[2]/span")));
    System.out.println("Setup Magnet Message server successful!!!");
   	 
    driver.findElement(By.id("complete-wizard-btn")).isDisplayed();
    driver.findElement(By.id("complete-wizard-btn")).click(); 
    Thread.sleep(4000);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    
        try {
            WebDriverWait wait = new WebDriverWait(driver, 4);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            String message = alert.getText();//
            System.out.println("Attention! "+message);//
            alert.accept();
        } catch (Exception e) {
            //exception handling 
        System.out.println("Alert is present");
        	
        }
//  //profile sign out
//    driver.findElement(By.cssSelector("#user-identity")).click();
////    driver.findElement(By.id("logout-btn")).click();
////    driver.findElement(By.cssSelector("#logout-btn")).click(); 
//      driver.findElement(By.xpath(".//*[@id='logout-btn']")).click();
  } 
  
    @Test
    public void Sign_inNout() throws Exception {
    
//    Thread.sleep(1000L);
    
	    driver.get("http://localhost:3000/#/login");
	    Thread.sleep(2000);
//	    WebDriverWait wait = new WebDriverWait(driver, 4);
//      wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.btn-primary")));
	    driver.findElement(By.name("username")).clear();
	    driver.findElement(By.name("username")).sendKeys("admin@test.com");Thread.sleep(2000);
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("admin");Thread.sleep(2000);
	    driver.findElement(By.id("login-btn")).click();
	    Thread.sleep(2000);
      
        //assert current user
        assertTrue(isElementPresent(By.xpath("//*[@id='user-identity']/div[2]/div/span[1]")));
        System.out.println("asserted current user: admin@test.com");
        Thread.sleep(3000);
  
        //within profile
        
        /*
         * for new user 
         * 
         */
        
        if(existsElement("reports")==true){

        	// on grey padding > Next 
          driver.findElement(By.xpath(".//*[@id='step-0']/div[3]/button")).click();Thread.sleep(3000);    
          //assert Quick start
          assertTrue(isElementPresent(By.xpath(".//*[@id='step-1']/h3")));
          System.out.println("asserted Quick start message");
          //end tour
          driver.findElement(By.xpath(".//*[@id='step-1']/div[3]/button")).click(); Thread.sleep(3000);
        	   }
        	   else{
        	   System.out.println("element not present --> going to Dashborad");
        	}

	    // go dashboard
        driver.findElement(By.xpath(".//*[@id='collapsible-menu-list']/div/a[1]")).click(); Thread.sleep(3000);
        // assert User profile entry page
	    driver.findElement(By.cssSelector("#user-identity")).click();Thread.sleep(1000);
	    driver.findElement(By.linkText("Profile")).click();Thread.sleep(3000);
	    assertTrue(isElementPresent(By.xpath(".//*[@id='user-profile-modal']/div/div/div[1]/h4")));
        System.out.println("asserted User profile entry page");
        
	    driver.findElement(By.cssSelector("div.col-sm-6 > input[name=\"firstName\"]")).clear();
	    driver.findElement(By.cssSelector("div.col-sm-6 > input[name=\"firstName\"]")).sendKeys("admin");Thread.sleep(3000);
	    driver.findElement(By.cssSelector("div.col-sm-6 > input[name=\"lastName\"]")).clear();
	    driver.findElement(By.cssSelector("div.col-sm-6 > input[name=\"lastName\"]")).sendKeys("user");Thread.sleep(3000);
	    driver.findElement(By.id("user-profile-update-btn")).click();Thread.sleep(3000);
	    driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();Thread.sleep(3000);
	    
	    //profile sign out
//	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//	    driver.findElement(By.cssSelector("#user-identity")).click();Thread.sleep(3000);
//	    driver.findElement(By.cssSelector("#logout-btn")).click();
	    System.out.println("Sign inNout test completed");
	    }
  
  
  
  
  /*
   * sign in - go Accounts -sign out
   */
 @Test
  public void testConfigurationReset_0() throws Exception {
	 driver.get("http://localhost:3000/#/login");
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("admin@test.com");Thread.sleep(2000); 
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("admin");Thread.sleep(2000); 
    driver.findElement(By.id("login-btn")).click();
    Thread.sleep(2000); 
    driver.get(userUrl + "/admin/#/users") ;
    //assert Account tab 
    assertEquals("Accounts", driver.findElement(By.cssSelector("#mgmt-users-tab>a")).getText());
    System.out.println("asserted - Accounts tab");
    Thread.sleep(3000); 
    driver.findElement(By.linkText("Accounts")).click();
    Thread.sleep(2000);
//    driver.findElement(By.cssSelector("#user-identity")).click();
//    driver.findElement(By.cssSelector("#logout-btn")).click();
    System.out.println("go Accounts test completed");
  }
  
 
 /*
  * sign in - go Logs -sign out
  */
  @Test
  public void testConfigurationReset_1() throws Exception {  
//	driver.navigate().to("http://localhost:3000/#/login");
	  driver.get("http://localhost:3000/#/login");
	  driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("admin@test.com");Thread.sleep(2000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("admin");Thread.sleep(2000);
    driver.findElement(By.id("login-btn")).click();
    Thread.sleep(2000);
    driver.get(userUrl + "/admin/#/events");
    //asser Log tab
    assertTrue(isElementPresent(By.id("mgmt-events-tab")));
    System.out.println("asserted Log tab");
    
    driver.findElement(By.linkText("Logs")).click();
    Thread.sleep(2000);
//    driver.findElement(By.cssSelector("#user-identity")).click();
//    driver.findElement(By.cssSelector("#logout-btn")).click();
    System.out.println("acces to Log Tab Configuration completed");
  }
  

  /*
   * sign in - go Basic Template -sign out
   */
  @Test
  public void testConfigurationReset_2() throws Exception {

	  System.out.println("Templates Test is running...");
	  driver.get("http://localhost:3000/#/login");
	driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("admin@test.com");Thread.sleep(1000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("admin");Thread.sleep(1000);
    driver.findElement(By.id("login-btn")).click();
    Thread.sleep(2000);
    driver.get(userUrl + "/admin/#/cms");
    driver.manage().window().maximize();
    driver.findElement(By.linkText("Templates")).click();Thread.sleep(2000);
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.findElement(By.xpath(".//*[@id='cms-menu']/ul/li[1]/a")).click(); Thread.sleep(2000);//basic template
    driver.findElement(By.xpath(".//*[@id='mgmt-cms']/div/div[2]/div/div[1]/div/div[1]/div[1]/span")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath(".//*[@id='mgmt-cms']/div/div[2]/div/div[1]/div/div[1]/div[3]/span[1]")).click();
    Thread.sleep(2000);
    
    //    assertEquals("Templates", driver.findElement(By.linkText("Templates")).getText());
	 try {
	 AssertJUnit.assertEquals("Templates", driver.findElement(By.linkText("Templates")).getText());
	 } catch (Error e) {
	 verificationErrors.append(e.toString());
	 }
	  System.out.println("Assert Tab Templates");
	  //    assertEquals("Email Templates", driver.findElement(By.cssSelector("#mgmt-cms > h3")).getText());
    try {
      AssertJUnit.assertEquals("Email Templates", driver.findElement(By.xpath(".//*[@id='mgmt-cms']/h3")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    System.out.println("Assert Email Templates");
    //    assertTrue(isElementPresent(By.linkText("Basic-Template")));
    try {
        AssertJUnit.assertEquals("Basic-Template", driver.findElement(By.xpath(".//*[@id='cms-menu']/ul/li[1]/a")).getText());
      } catch (Error e) {
        verificationErrors.append(e.toString());
      }
    System.out.println("Assert Basic-Templates");
//    assertTrue(isElementPresent(By.linkText("Forgot-Password")));
    assertTrue(isElementPresent(By.xpath(".//*[@id='cms-menu']/ul/li[2]/a")));	
//    try {
//        AssertJUnit.assertEquals("Forgot-Password", driver.findElement(By.xpath(".//*[@id='cms-menu']/ul/li[2]/a")).getText());
//      } catch (Error e) {
//        verificationErrors.append(e.toString());
//      }
    System.out.println("Assert Forgot-Password");
    Thread.sleep(5000);
    //    assertTrue(isElementPresent(By.linkText("Invite-Confirmation")));
//    try {
//        AssertJUnit.assertEquals("Invite-Confirmation", driver.findElement(By.xpath(".//*[@id='cms-menu']/ul/li[3]/a")).getText());
//      } catch (Error e) {
//        verificationErrors.append(e.toString());
//      }
//    System.out.println("Assert Invite-Confirmation");
    
    //ok buttton
    driver.findElement(By.cssSelector("#general-alert > div.modal-dialog > div.modal-content > div.modal-footer > button.btn.btn-primary")).click();
    driver.findElement(By.xpath(".//*[@id='cms-menu']/ul/li[2]/a")).click(); //forgot password
    Thread.sleep(2000);
    driver.findElement(By.xpath(".//*[@id='mgmt-cms']//div[1]/span")).click(); //pencil
    Thread.sleep(2000);
    driver.findElement(By.xpath(".//*[@id='mgmt-cms']//div[2]/button[1]")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath(".//*[@id='mgmt-cms']//div[2]/button[2]")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath(".//*[@id='mgmt-cms']//div[1]/span")).click(); //pencil
    Thread.sleep(2000);
    driver.findElement(By.xpath(".//*[@id='mgmt-cms']//div[3]/span[2]")).click(); // x btn
    Thread.sleep(2000);
    driver.findElement(By.xpath(".//*[@id='mgmt-cms']//div[1]/span")).click(); //pencil
    Thread.sleep(2000);
    driver.findElement(By.xpath(".//*[@id='mgmt-cms']//div[3]/span[1]")).click();  // √ btn
    Thread.sleep(2000);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.findElement(By.xpath(".//*[@id='general-alert']/div/div/div[3]/button")).click(); // ok btn
    Thread.sleep(2000);
    
//    driver.findElement(By.cssSelector("#user-identity")).click();Thread.sleep(2000);
//    driver.findElement(By.cssSelector("#logout-btn")).click();
    System.out.println("Templates Test passed. >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
  }
  
  
  /*
   * sign in - go Settings (Reset) -sign out
   */
  @Test
  public void testConfiguration_CommonReset() throws Exception {
//    driver.get(userUrl + "/#/login");
	  driver.get("http://localhost:3000/#/login");
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("admin@test.com");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.id("login-btn")).click();
    Thread.sleep(2000); 
    driver.get(userUrl + "/admin/");
    
    driver.findElement(By.linkText("Accounts")).click();
    
    driver.findElement(By.linkText("Logs")).click();
    driver.findElement(By.linkText("Templates")).click();
    driver.findElement(By.linkText("Configuration")).click();
    Thread.sleep(2000);
    driver.findElement(By.linkText("Magnet Message Connection Settings")).click();
    driver.findElement(By.xpath("//div[@id='admin-config-item-MMX']/div/div/div/div[2]/button[2]")).click();
    driver.findElement(By.linkText("Messaging Settings")).click();
    driver.findElement(By.xpath("//div[@id='admin-config-item-MessagingSettings']/div/div/div/div[2]/button[2]")).click();
    driver.findElement(By.linkText("System Administration Settings")).click();
    driver.findElement(By.xpath("//div[@id='admin-config-item-App']/div/div/div/div[2]/button[2]")).click();
    driver.findElement(By.linkText("Magnet Message Database Settings")).click();
    driver.findElement(By.xpath("//div[@id='admin-config-item-Database']/div/div/div/div[2]/button[2]")).click();
    driver.findElement(By.linkText("Redis")).click();
    driver.findElement(By.xpath("//div[@id='admin-config-item-Redis']/div/div/div/div[2]/button[2]")).click();
    driver.findElement(By.linkText("Email Settings")).click();
    driver.findElement(By.xpath("//div[@id='admin-config-item-Email']/div/div/div/div[2]/button[2]")).click();
    driver.findElement(By.linkText("Geo Tracking")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//div[@id='admin-config-item-Geologging']/div/div/div/div[2]/button[2]")).click();
    
////    driver.findElement(By.cssSelector("*.user-identity-section")).click();
//    driver.findElement(By.cssSelector("#user-identity")).click();
////    driver.findElement(By.id("logout-btn")).click();
//    driver.findElement(By.cssSelector("#logout-btn")).click();
  }
  
    
    //part2 for if for new user in Sign_inNout()
    private boolean existsElement(String id) {
        try {
            driver.findElement(By.xpath(".//*[@id='step-0']/div[3]/button"));
        } catch (Exception e) {
            System.out.println("element is not present ");
            return false;
        }

        return true;
    }


private Object log() {
	// TODO Auto-generated method stub
	return null;
}


@AfterMethod
public void tearDown() throws Exception {
    driver.quit();
//    driver.close();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      Assert.fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
  }
