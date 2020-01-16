package quiz.crawler;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium {

   // WebDriver
   private WebDriver driver;

   // Properties
   public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
   public static final String WEB_DRIVER_PATH = "C:\\0.encore\\00.lib\\chromedriver.exe";

   // 크롤링 할 URL
//   private String base_url;

   public Selenium() {
      super();
      // System Property SetUp
      System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
      // Driver SetUp
      driver = new ChromeDriver();
   }

   public WebDriver getDriver() {
      return driver;
   }
   
   

}