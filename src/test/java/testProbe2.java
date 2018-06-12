

import java.io.File;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class testProbe2 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  public testProbe2() {
         System.setProperty("webdriver.gecko.driver", "C:\\Users\\Gruszka Ilona\\Documents\\FH ILONA\\2 SEMESTR\\TESTWERGZEUG\\geckodriver-v0.17.0-win64\\geckodriver.exe");
  }
  
  @Before
  public void setUp() throws Exception {
      
        File pathBinary = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);   
        DesiredCapabilities desired = DesiredCapabilities.firefox();
        FirefoxOptions options = new FirefoxOptions();
        desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
      
    driver = new FirefoxDriver(options);
    baseUrl = "https://s1.demo.opensourcecms.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    
  }

  @Test
  public void testProbe2() throws Exception {
      
      //Variablen speichern
    String Page = "Seite Neu Ilona";
    String titlePost = "TestTestTestPost";
    String textPage = "hahahahaha";
    String textPost = "Es ist mein erster Post";
    String user_pass = "opensourcecms";
    String titleCategory = "Wetter";
    String titleTag = "Wetter";
    
    
    
    
    
    //Login
    driver.get(baseUrl + "/wordpress/wp-login.php");
 
    driver.findElement(By.cssSelector("input[id^='user_log']")).sendKeys(user_pass);
 
    driver.findElement(By.cssSelector("input[id^='user_pa']")).sendKeys(user_pass);
    driver.findElement(By.id("wp-submit")).click();
  
        for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("Dashboard ‹ My CMS — WordPress".equals(driver.getTitle())) break; } catch (Exception e) {}
    	Thread.sleep(3000);
    }
    System.out.println("Login passed 1111111111111111111111111111111111");    
    
    
    //New Page erstellen
    driver.findElement(By.xpath("//li[@id='menu-pages']/a/div[3]")).click();
       for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if ("Pages ‹ My CMS — WordPress".equals(driver.getTitle())) break; } catch (Exception e) {}
    	Thread.sleep(3000);
    }
    System.out.println("Klick auf Pages");   
        
      
   
    Thread.sleep(3000);
    
   
    driver.findElement(By.xpath("//a[contains(@href, 'post-new.php?post_type=page')]")).click();  
    System.out.println("Neue Page geöffnet");   
        
    Thread.sleep(3000);
   
    driver.findElement(By.id("title")).sendKeys(Page); 
    Thread.sleep(3000);
    
   
    driver.findElement(By.id("content")).clear();
    driver.findElement(By.id("content")).sendKeys(textPage);
   
    
     
    Thread.sleep(3000);
   driver.findElement(By.cssSelector("input[id='publish']")).click();
Thread.sleep(3000);
   System.out.println("Seite ist erstellt"); 
       for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.xpath("//div[@id='message']/p"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }       
       //Ueberprufen ob Seite da ist
Thread.sleep(3000);
    try {
      assertEquals("Page published. View page", driver.findElement(By.xpath("//div[@id='message']/p")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals(Page, driver.findElement(By.cssSelector("input[id^='titl']")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals(textPage, driver.findElement(By.cssSelector("#content")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    
    System.out.println("Seite ist Ueberprueft");
    
  
//Seite wird bearbeitet
    Thread.sleep(5000);
    String titlePageUpdated = "1 Seite Ilona";
    driver.findElement(By.cssSelector("input[id=title]")).clear();
    driver.findElement(By.cssSelector("input[id=title]")).sendKeys(titlePageUpdated);
    String textPageUpdated = "Seite bearbeitet";
    driver.findElement(By.cssSelector("#content")).clear();
    driver.findElement(By.cssSelector("#content")).sendKeys(textPageUpdated);
    
    System.out.println("Content ist rein");
    Thread.sleep(1000);
      
    	
    driver.findElement(By.cssSelector("#publish")).click();
    
    System.out.println("Seite wurde bearbeitet");
    
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.xpath("//div[@id='message']/p"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
    Thread.sleep(5000);

    try {
      assertEquals("Page updated. View page", driver.findElement(By.xpath("//div[@id='message']/p")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals(titlePageUpdated, driver.findElement(By.cssSelector("input[id^='titl']")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals(textPageUpdated, driver.findElement(By.cssSelector("#content")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.get("https://s1.demo.opensourcecms.com/wordpress/wp-admin/post-new.php");
    driver.findElement(By.cssSelector("input[id^='titl']")).clear();
    driver.findElement(By.cssSelector("input[id^='titl']")).sendKeys(titlePost);
    driver.findElement(By.cssSelector("#content")).clear();
    driver.findElement(By.cssSelector("#content")).sendKeys(textPost);
    Thread.sleep(1000);
    driver.findElement(By.cssSelector("#publish")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.cssSelector("#message > p"))) break; } catch (Exception e) {}
    	Thread.sleep(3000);
    }

    try {
      assertEquals("Post published. View post", driver.findElement(By.cssSelector("#message > p")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals(titlePost, driver.findElement(By.cssSelector("input[id^='titl']")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals(textPost, driver.findElement(By.cssSelector("#content")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    
    
    driver.get("https://s1.demo.opensourcecms.com/wordpress/wp-admin/edit-tags.php?taxonomy=category");
    driver.findElement(By.cssSelector("input[id^='tag-na']")).clear();
    driver.findElement(By.cssSelector("input[id^='tag-na']")).sendKeys(titleCategory);
    driver.findElement(By.cssSelector("input[id^='submi']")).click();
    driver.get("https://s1.demo.opensourcecms.com/wordpress/wp-admin/edit-tags.php?taxonomy=post_tag");
    driver.findElement(By.cssSelector("input[id^='tag-na']")).clear();
    driver.findElement(By.cssSelector("input[id^='tag-na']")).sendKeys(titleTag);
    driver.findElement(By.cssSelector("input[id^='submi']")).click();
    
   
    
    driver.get("https://s1.demo.opensourcecms.com/wordpress/");
   driver.findElement(By.cssSelector("#adminbar-search")).click();
    //driver.findElement(By.cssSelector("#adminbar-search")).clear();
    Thread.sleep(3000);
    
   driver.findElement(By.cssSelector("#adminbar-search")).sendKeys(titlePageUpdated);
   Thread.sleep(3000);
    
    driver.findElement(By.cssSelector("#adminbar-search")).click();
    driver.findElement(By.cssSelector("a.menu-scroll-down")).click();
     Thread.sleep(1000);
        
     driver.findElement(By.cssSelector(".search-field")).sendKeys(titlePageUpdated);
      driver.findElement(By.cssSelector(".search-submit")).click();
  
    try {
      assertEquals(titlePageUpdated, driver.findElement(By.linkText(titlePageUpdated)).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    assertEquals(textPageUpdated, driver.findElement(By.xpath("//article/div/p")).getText() );
  
 
    driver.findElement(By.cssSelector(".search-field")).clear();
    driver.findElement(By.cssSelector(".search-field")).sendKeys(titlePost);
    driver.findElement(By.cssSelector("button.search-submit")).click();
       Thread.sleep(5000);
   
    try {
      assertEquals(textPost, driver.findElement(By.xpath("//article/div/p")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
driver.findElement(By.cssSelector("span.display-name")).click();
  
}
  

  @After
  public void tearDown() throws Exception {
    System.out.println("hier");
      driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
    
    System.out.println("hier2");
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
