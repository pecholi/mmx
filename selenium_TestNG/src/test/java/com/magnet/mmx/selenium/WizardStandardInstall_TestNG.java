package com.magnet.mmx.selenium;


import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;


public class WizardStandardInstall_TestNG {
  private WebDriver driver;
  private String baseUrl;
  private String userUrl;
  private StringBuffer verificationErrors = new StringBuffer();
  
 
  @BeforeMethod

public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://magnet.com";
    userUrl = "http://localhost:3000";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

 
  /*
   * Standard install  
   */ 
  @Test
  public void test_0__StandardInstall() throws Exception {
	  System.out.println("Standard install is running...");
		driver.navigate().to("http://localhost:3000/wizard");
		Thread.sleep(2000);
		System.out.println("Waiting for user setup...");
		
		//step1

	    //assert step 1
	    AssertJUnit.assertEquals("Welcome", driver.findElement(By.cssSelector("h4")).getText());
	    System.out.println("asserted Welcome Page");
	   
	  //assert Standard install Button 
	    try {
	    	  WebElement e = driver.findElement(By.xpath("//*[@id='project-wizard-container']/div[2]/div[1]/div[1]/div/button[1]"));
	    	  AssertJUnit.assertTrue(e.isDisplayed());
	    	} catch(NoSuchElementException nsee) {
	    	   System.out.println("Standard install Button is not found");
	    	} catch(AssertionError ae) {
	    	   System.out.println("Standard install Buttonasertion Error.");
	    	}
	    System.out.println("assertion of Standard install Button");
	    
	    //assert Next button
	    AssertJUnit.assertEquals("Next", driver.findElement(By.cssSelector(".wiz-next.btn.btn-primary")).getText());
        System.out.println("asserted Next button >");
        driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();//Next
        
        
        //if  Messaging Server Already Configured .//*[@id='confirm-alert']/div/div/div[1]/h4 ...Yes  .//*[@id='confirm-alert']/div/div/div[3]/button[1]
       // go step 4

        Thread.sleep(2000);
        // assert progress bar
	    AssertJUnit.assertEquals("Please Wait...", driver.findElement(By.cssSelector("#wait-modal h4")).getText());
	    System.out.println("asserted progress bar >>>");
	    
	    System.out.println("Going to the final step");
	    Thread.sleep(10000);
	    //step5
   
	    //assert Setup Admin Account == Success
	    try {
  	  WebElement e = driver.findElement(By.xpath("//table[@id='wizard-summary-table']/tbody/tr[4]/td[2]/span"));
  	  AssertJUnit.assertTrue(e.isDisplayed());
  	} catch(NoSuchElementException nsee) {
  	   System.out.println("Admin Account setup failed.");
  	} catch(AssertionError ae) {
  	   System.out.println("Admin Account setup Error.");
  	}
	    // assert Setup Magnet Message == Success
	    try {
	    	  WebElement e = driver.findElement(By.xpath("//table[@id='wizard-summary-table']/tbody/tr[6]/td[2]/span"));
	    	  AssertJUnit.assertTrue(e.isDisplayed());
	    	} catch(NoSuchElementException nsee) {
	    		System.out.println("Magnet Message setup failed.");
	    	} catch(AssertionError ae) {
	    	   System.out.println("Magnet Message setup Error");
	    	}
	    
	    // assert Complete Setup button
	    AssertJUnit.assertEquals("Complete Setup", driver.findElement(By.cssSelector("#complete-wizard-btn")).getText());
	    System.out.println("asserted Complete Setup button");
	    driver.manage().window().maximize();
	    driver.findElement(By.id("complete-wizard-btn")).click();
	    Thread.sleep(5000);
	    
	    System.out.println("Setup DB, Admin Account & Setup Magnet Message successful!!!");
  
//	    driver.findElement(By.cssSelector("#user-identity")).click();Thread.sleep(2000);
//	    driver.findElement(By.cssSelector("#logout-btn")).click();
	    System.out.println("Standard install passed.");
	    
	    	}



@Test
  public void test_1_ConfigurationReset_Accounts() throws Exception {
	System.out.println("Reset Accounts Test is running ...");
	driver.get("http://localhost:3000/#/login");
	//login page assert
	AssertJUnit.assertEquals("Sign In", driver.findElement(By.cssSelector(".panel.panel-default>h4")).getText());
    System.out.println("asserted Sign In Page");
	
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("sysadmin@company.com");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("admin");
    Thread.sleep(3000); 
    //assert login button
    AssertJUnit.assertEquals("Sign In", driver.findElement(By.cssSelector("#login-btn")).getText());
    System.out.println("asserted Sign In Button");
    
    //user login
    driver.findElement(By.id("login-btn")).click();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    Thread.sleep(3000); 
    
    //assert user login
    AssertJUnit.assertEquals("sysadmin@company.com", driver.findElement(By.cssSelector(".placeholder-username")).getText());
    System.out.println("asserted - user Signed In ");
    Thread.sleep(3000); 
    
    driver.get(userUrl + "/admin/");
    Thread.sleep(2000);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    //assertion of Admin console
    try {
      AssertJUnit.assertEquals("Administration", driver.findElement(By.xpath(".//*[@id='page-select']/div")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    System.out.println("asserted - user on Admin Console");
    Thread.sleep(3000); 
    
    //assert Account tab 
    AssertJUnit.assertEquals("Accounts", driver.findElement(By.cssSelector("#mgmt-users-tab>a")).getText());
    System.out.println("asserted - Accounts tab");
    Thread.sleep(3000); 
    driver.findElement(By.linkText("Accounts")).click();
    Thread.sleep(3000);
    
    driver.findElement(By.id("mgmt-accounts-show-create-modal-btn")).click();
    driver.findElement(By.name("firstName")).clear();
    driver.findElement(By.name("firstName")).sendKeys("n");

 	driver.findElement(By.name("firstName")).sendKeys("e");
 	
 	driver.findElement(By.name("firstName")).sendKeys("w");
 	
 	driver.findElement(By.name("firstName")).sendKeys("u");
 	
 	driver.findElement(By.name("firstName")).sendKeys("s");
 	
 	driver.findElement(By.name("firstName")).sendKeys("e");
 	
 	driver.findElement(By.name("firstName")).sendKeys("r");
 	driver.findElement(By.name("firstName")).sendKeys("b");
 	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   
   driver.findElement(By.name("lastName")).clear();
   driver.findElement(By.name("lastName")).sendKeys("n");
   driver.findElement(By.name("lastName")).sendKeys("e");
   driver.findElement(By.name("lastName")).sendKeys("w");
   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

   driver.findElement(By.name("email")).clear();
   driver.findElement(By.name("email")).sendKeys("a");
   driver.findElement(By.name("email")).sendKeys("d");
   driver.findElement(By.name("email")).sendKeys("m");
   driver.findElement(By.name("email")).sendKeys("i");
   driver.findElement(By.name("email")).sendKeys("n");
   driver.findElement(By.name("email")).sendKeys("s");
   driver.findElement(By.name("email")).sendKeys("@");
   driver.findElement(By.name("email")).sendKeys("t");
   driver.findElement(By.name("email")).sendKeys("e");
   driver.findElement(By.name("email")).sendKeys("s");
   driver.findElement(By.name("email")).sendKeys("t");
   driver.findElement(By.name("email")).sendKeys(".");
   driver.findElement(By.name("email")).sendKeys("c");
   driver.findElement(By.name("email")).sendKeys("o");
   driver.findElement(By.name("email")).sendKeys("m");
   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   
   driver.findElement(By.name("password")).clear();
   driver.findElement(By.name("password")).sendKeys("a");
   driver.findElement(By.name("password")).sendKeys("d");
   driver.findElement(By.name("password")).sendKeys("m");
   driver.findElement(By.name("password")).sendKeys("i");
   driver.findElement(By.name("password")).sendKeys("n");
   
   driver.findElement(By.id("mgmt-accounts-create-btn")).click(); Thread.sleep(2000);//Add
   
   WebDriverWait wait = new WebDriverWait(driver, 5);
   
//   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#user-identity")));
//   driver.findElement(By.xpath(".//*[@id='user-identity']/div[1]/span[2]")).click();Thread.sleep(2000);
////   driver.findElement(By.cssSelector("#user-identity")).click();
//   driver.findElement(By.xpath(".//*[@id='logout-btn']")).click();
////   driver.findElement(By.id("logout-btn")).click();
////   WebDriverWait wait2 = new WebDriverWait(driver, 15);
////   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#logout-btn")));
////   driver.findElement(By.cssSelector("#logout-btn")).click();
   System.out.println("Reset Accounts Test passed.>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
}

@Test
public void test_1_ConfigurationReset_NewUserLogin() throws Exception {
	System.out.println("New User Login Test Test is running ...");
	driver.get("http://localhost:3000/#/login");
   driver.findElement(By.name("username")).clear();
   driver.findElement(By.name("username")).sendKeys("admins@");
   driver.findElement(By.name("username")).sendKeys("test.com");
   driver.findElement(By.name("password")).clear();
   driver.findElement(By.name("password")).sendKeys("a");
   driver.findElement(By.name("password")).sendKeys("d");
   driver.findElement(By.name("password")).sendKeys("m");
   driver.findElement(By.name("password")).sendKeys("i");
   driver.findElement(By.name("password")).sendKeys("n");
   driver.findElement(By.id("login-btn")).click();
   Thread.sleep(2000); 
   
 //assert logged in user 
 driver.findElement(By.xpath("//*[@id='user-identity']/div[2]/div/span[1]")).click();
 try {
   AssertJUnit.assertEquals("admins@test.com", driver.findElement(By.cssSelector("span.placeholder-username")).getText());
   log("User is loogged in");
 } catch (Error e) {
   verificationErrors.append(e.toString());
 }
 System.out.println("Assert logged in user");
// //logout
//    driver.findElement(By.cssSelector("#user-identity")).click();Thread.sleep(2000);
//    driver.findElement(By.xpath(".//*[@id='logout-btn']")).click();
////    driver.findElement(By.cssSelector("#logout-btn")).click();
    System.out.println("New User Login Test passed.>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
  }
  



  @Test
  public void test_2_ConfigurationReset_Logs() throws Exception {
	  System.out.println("Reset Logs Test is running ...");
	  driver.get("http://localhost:3000/#/login");
	driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("sysadmin@company.com");Thread.sleep(2000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("admin");Thread.sleep(2000);
    driver.findElement(By.id("login-btn")).click();
    Thread.sleep(2000);
    driver.get(userUrl + "/admin/#/events");
    
    driver.findElement(By.linkText("Logs")).click();
    System.out.println("click_1");
   log("driver.get(baseUrl + \"/admin/#/events\"");
    driver.findElement(By.cssSelector("span > button.btn.btn-default")).click();Thread.sleep(2000);
    System.out.println("click_2");
    driver.findElement(By.cssSelector("div.input-container > input.form-control")).clear();Thread.sleep(2000);
    System.out.println("click_3");
    driver.findElement(By.cssSelector("div.input-container > input.form-control")).sendKeys("info");Thread.sleep(2000);
    System.out.println("click_4");
    driver.findElement(By.xpath("//div[@id='mgmt-events-list']/div/div[2]/div/div[2]/button[2]")).click();Thread.sleep(2000);
    System.out.println("click_5");
    driver.findElement(By.linkText("Level")).click();Thread.sleep(2000);
    System.out.println("click_6");
  //scroll down - OK
    JavascriptExecutor jse = (JavascriptExecutor)driver;
    jse.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
    System.out.println("scroll down > executed");
    //click search btn
    driver.findElement(By.xpath(".//*[@id='mgmt-events-list']/div[1]/div[2]/div/div[2]/button[3]")).click();Thread.sleep(2000);
//    driver.findElement(By.cssSelector("div.input-append > div.btn-group > button.btn.btn-primary")).click();Thread.sleep(2000);
    System.out.println("click_7");
    // reload page to get Timestamp button to click in step 8
    driver.get(userUrl + "/admin/#/events");
    driver.get(userUrl + "/admin/#/actions");
    driver.get(userUrl + "/admin/#/events");
    driver.manage().window().maximize();
//    //scroll up 
//    JavascriptExecutor jse1 = (JavascriptExecutor)driver;
//    jse.executeScript("scroll(250, 0)"); //x value '250' can be altered
    driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();Thread.sleep(2000);
    System.out.println("click_8");
    //   //assert logs sorted by Level
//    AssertJUnit.assertEquals("info", driver.findElement(By.xpath("//div[@id='mgmt-events-list']/table/tbody/tr/td[2]")).getText());
//    log("PASS: Element was on the page"); 
    System.out.println("Assert log sorted by Level info");
    Thread.sleep(2000);
   log("Magnet Message Database Log Settings"); 
    driver.findElement(By.linkText("Magnet Message Database Log Settings")).click();
    Thread.sleep(5000);
    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
    Thread.sleep(5000);
    driver.findElement(By.xpath("//button[@type='button']")).click();
    Thread.sleep(2000);
   log("maximize, log levels"); 
    driver.manage().window().maximize();
    new Select(driver.findElement(By.name("level"))).selectByVisibleText("error");Thread.sleep(5000);
    new Select(driver.findElement(By.name("level"))).selectByVisibleText("debug");Thread.sleep(5000);
    new Select(driver.findElement(By.name("level"))).selectByVisibleText("verbose");Thread.sleep(5000);
    new Select(driver.findElement(By.name("level"))).selectByVisibleText("info");Thread.sleep(5000);
    new Select(driver.findElement(By.name("level"))).selectByVisibleText("warn");Thread.sleep(5000);
    new Select(driver.findElement(By.name("level"))).selectByVisibleText("error");Thread.sleep(5000);
    
    System.out.println("Reset button to collapse");
    driver.findElement(By.xpath("//div[@id='admin-config-item-DatabaseLog']//div[2]/button[2]")).click();
    JavascriptExecutor jse2 = (JavascriptExecutor)driver;
    jse.executeScript("scroll(250, 0)"); //x value '250' can be altered
    Thread.sleep(5000);
    driver.findElement(By.linkText("Magnet Message Database Log Settings")).click();
    Thread.sleep(5000);
    driver.findElement(By.linkText("Magnet Message Database Log Settings")).click();
    Thread.sleep(5000);
    
    driver.findElement(By.linkText("File Log Settings")).click();
    Thread.sleep(5000);
    AssertJUnit.assertEquals("", driver.findElement(By.xpath("//div[@id='admin-config-item-FileLog']//div[5]/label/span")).getText());
    System.out.println("Verified Maximum log file Size");
    Thread.sleep(5000);
    driver.findElement(By.name("maxsize")).clear();
    driver.findElement(By.name("maxsize")).sendKeys("10");
    driver.findElement(By.name("maxFiles")).clear();
    driver.findElement(By.name("maxFiles")).sendKeys("5");
    Thread.sleep(3000);
    driver.findElement(By.xpath("//div[@id='admin-config-item-FileLog']/div/div/div/div[2]/button")).click();
    Thread.sleep(3000);
    System.out.println("Sorting Logs by order");
    driver.findElement(By.cssSelector("#general-alert > div.modal-dialog > div.modal-content > div.modal-footer > button.btn.btn-primary")).click();
    Thread.sleep(5000);
    driver.findElement(By.linkText("File Log Settings")).click();
    Thread.sleep(5000);
  //keep for test case with disabled Email in Configuration & Link becomes visible
//    driver.findElement(By.linkText("Email Alerts")).click();
//    Thread.sleep(5000);
//    try {
//      AssertJUnit.assertEquals("Click here to set up email", driver.findElement(By.linkText("Click here to set up email")).getText());
//    } catch (Error e) {
//      verificationErrors.append(e.toString());
//    }
//    System.out.println("Assert email link");
//    Thread.sleep(3000);
//    driver.findElement(By.linkText("Email Alerts")).click();
//    //logout
//    driver.findElement(By.cssSelector("#user-identity")).click();Thread.sleep(2000);
//    driver.findElement(By.cssSelector("#logout-btn")).click();
    System.out.println("Reset Logs Test passed.>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
  }

  
  private void log(String string) {
	// TODO Auto-generated method stub
	
}


@Test
  public void test_3_ConfigurationReset_Template() throws Exception {
	  
	  System.out.println("Templates Test is running...");
	  driver.get("http://localhost:3000/#/login");
	driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("sysadmin@company.com");Thread.sleep(1000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("admin");Thread.sleep(1000);
    driver.findElement(By.id("login-btn")).click();
    Thread.sleep(2000);
    driver.get(userUrl + "/admin/");
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
//    assertTrue(isElementPresent(By.xpath(".//*[@id='cms-menu']/ul/li[2]/a")));
////    try {
////        AssertJUnit.assertEquals("Forgot-Password", driver.findElement(By.xpath(".//*[@id='cms-menu']/ul/li[2]/a")).getText());
////      } catch (Error e) {
////        verificationErrors.append(e.toString());
////      }
//    System.out.println("Assert Forgot-Password");
//    assertTrue(isElementPresent(By.linkText("Invite-Confirmation")));
    try {
        AssertJUnit.assertEquals("Invite-Confirmation", driver.findElement(By.xpath(".//*[@id='cms-menu']/ul/li[3]/a")).getText());
      } catch (Error e) {
        verificationErrors.append(e.toString());
      }
    System.out.println("Assert Invite-Confirmation");
    
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
//    //logout
//    driver.findElement(By.cssSelector("#user-identity")).click();Thread.sleep(2000);
//    driver.findElement(By.cssSelector("#logout-btn")).click();
    System.out.println("Templates Test passed. >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	  
  }
 
  
  
  @Test
  public void test_4_ConfigurationReset_allTabs() throws Exception {
	  System.out.println("Configuration Reset Test is running...");
    driver.get(userUrl + "/#/login");
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("sysadmin@company.com");Thread.sleep(2000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("admin");Thread.sleep(2000);
    driver.findElement(By.id("login-btn")).click();
    Thread.sleep(2000); 
    driver.get(userUrl + "/admin/#/actions");
    
//    driver.findElement(By.linkText("Accounts")).click();
//    driver.findElement(By.linkText("Logs")).click();
//    driver.findElement(By.linkText("Templates")).click();
    driver.findElement(By.linkText("Configuration")).click();
    try {
      AssertJUnit.assertEquals("Configuration", driver.findElement(By.xpath(".//*[@id='mgmt-actions-tab']/a")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  System.out.println("Assert Configuration Tab");
    Thread.sleep(2000);
    
    driver.findElement(By.linkText("Magnet Message Connection Settings")).click();Thread.sleep(4000); 
    try {
        AssertJUnit.assertEquals("Magnet Message Connection Settings", driver.findElement(By.xpath(".//*[@id='admin-config-container-collapsible']/div[1]/div[1]/h4/a")).getText());
      } catch (Error e) {
        verificationErrors.append(e.toString());
      }
    System.out.println("Assert Magnet Message Connection Settings");
    driver.manage().window().maximize();
    driver.findElement(By.xpath("//div[@id='admin-config-item-MMX']/div/div/div/div[2]/button[2]")).click();Thread.sleep(4000); 
    
    driver.findElement(By.linkText("Messaging Settings")).click();
    driver.findElement(By.xpath("//div[@id='admin-config-item-MessagingSettings']/div/div/div/div[2]/button[2]")).click();
    
    driver.findElement(By.linkText("System Administration Settings")).click();Thread.sleep(2000); 
    driver.findElement(By.xpath(".//*[@id='admin-config-item-App']/div/div/div[1]/div[1]/div[3]/div/button[2]")).click();Thread.sleep(2000); 
    driver.findElement(By.xpath("//*[@id='admin-config-item-App']/div/div/div[1]/div[1]/div[3]/div/button[1]")).click();Thread.sleep(2000); 
    driver.findElement(By.xpath("//div[@id='admin-config-item-App']/div/div/div/div[2]/button[2]")).click();
   
    driver.findElement(By.linkText("Magnet Message Database Settings")).click();Thread.sleep(4000); 
    driver.findElement(By.xpath("//div[@id='admin-config-item-Database']/div/div/div/div[2]/button[2]")).click();
   
    driver.findElement(By.linkText("Redis")).click();Thread.sleep(2000); 
    System.out.println("Enable - Disable Redis");
    driver.findElement(By.xpath(".//*[@id='admin-config-item-Redis']//div/button[1]")).click();
    driver.findElement(By.xpath(".//*[@id='admin-config-item-Redis']//div/button[2]")).click();
    driver.findElement(By.xpath("//div[@id='admin-config-item-Redis']/div/div/div/div[2]/button[2]")).click();
   
    driver.findElement(By.linkText("Email Settings")).click();Thread.sleep(2000);
    //scroll down
    JavascriptExecutor jse = (JavascriptExecutor)driver;
    jse.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
    System.out.println("scroll down > executed");Thread.sleep(2000);
    driver.findElement(By.xpath(".//*[@id='admin-config-item-Email']/div/div/div[1]/div[1]/div[1]/div/button[1]")).click();Thread.sleep(2000);
//    driver.findElement(By.cssSelector(".btn.btn-sm.btn-default")).click();Thread.sleep(2000); //Enable
//    driver.findElement(By.xpath(".//*[@id='admin-config-item-Email']/div/div/div[1]/div[1]/div[1]/div/button[1]")).click();//Enable
//    driver.findElement(By.xpath(".//*[@id='admin-config-item-Email']/div/div/div[1]/div[1]/div[1]/div/button[2]")).click();//reset
    System.out.println("Configuration Email settings.");    
        //emial    
	    driver.findElement(By.xpath(".//*[@id='admin-config-item-Email']/div/div/div[1]/div[1]/div[4]/input")).clear();
        driver.findElement(By.xpath(".//*[@id='admin-config-item-Email']/div/div/div[1]/div[1]/div[4]/input")).
    							sendKeys("email-smtp.us-east-1.amazonaws.com");Thread.sleep(2000);
    	//user
    	driver.findElement(By.xpath(".//*[@id='admin-config-item-Email']/div/div/div[1]/div[1]/div[6]/input")).clear();
    							driver.findElement(By.xpath(".//*[@id='admin-config-item-Email']/div/div/div[1]/div[1]/div[6]/input")).sendKeys("AKIAJTSTEPNSTK7VH26Q");
        //passw 
    	driver.findElement(By.xpath(".//*[@id='admin-config-item-Email']/div/div/div[1]/div[1]/div[7]/input")).clear();
    							driver.findElement(By.xpath(".//*[@id='admin-config-item-Email']/div/div/div[1]/div[1]/div[7]/input")).sendKeys("At2Qas96wh2+afdRVylF0lhiwWnE/Zo/jl+Od49H5Exb");
    	//sender
    	driver.findElement(By.xpath(".//*[@id='admin-config-item-Email']/div/div/div[1]/div[1]/div[3]/input")).clear();
    							driver.findElement(By.xpath(".//*[@id='admin-config-item-Email']/div/div/div[1]/div[1]/div[3]/input")).sendKeys("Magnet Developer Factory <no-reply@magnet.com>");
    //save							
    driver.findElement(By.xpath(".//*[@id='admin-config-item-Email']//div[2]/button[1]")).click();Thread.sleep(2000);
    
  //scroll down
    JavascriptExecutor jse1= (JavascriptExecutor)driver;
    jse.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
    System.out.println("scroll down > executed");Thread.sleep(2000);
    driver.findElement(By.xpath(".//*[@id='general-alert']//div[3]/button")).click();Thread.sleep(2000);
    driver.findElement(By.xpath("//div[@id='admin-config-item-Email']//div[2]/button[2]")).click();Thread.sleep(2000); 
 
    driver.findElement(By.linkText("Geo Tracking")).click();Thread.sleep(2000); 
    driver.findElement(By.xpath("//div[@id='admin-config-item-Geologging']//div[2]/button[2]")).click();
 //// sign out  
////  driver.findElement(By.cssSelector("*.user-identity-section")).click();
//    driver.findElement(By.cssSelector("#user-identity")).click();Thread.sleep(2000);
////  driver.findElement(By.id("logout-btn")).click();
//    driver.findElement(By.cssSelector("#logout-btn")).click();
    System.out.println("Configuration Reset Test passed. >>>>>>>>>>>>>>>>>>>>>>>>>>");
  }
  

private boolean isElementPresent(By linkText) {
	// TODO Auto-generated method stub
	return false;
}


@AfterMethod

public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      Assert.fail(verificationErrorString);
    }
  }
 
  
 }
