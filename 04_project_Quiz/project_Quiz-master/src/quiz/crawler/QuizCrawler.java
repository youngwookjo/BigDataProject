package quiz.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QuizCrawler {
   static WebDriver driver = null;
//   static JavascriptExecutor js = (JavascriptExecutor) driver;

   public static void main(String[] args) {

      try {
         crawlQuiz(1);
         wait(10);
         crawlQuiz(2);
         wait(10);
         crawlQuiz(3);
         wait(10);
         crawlQuiz(4);
         wait(10);
         crawlQuiz(5);
         
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public static ArrayList<String> crawlQuiz(int i) throws IOException {
      driver = new Selenium().getDriver();
      WebElement wel = null;
      ArrayList<String> TitleList = new ArrayList<>();
      String url = "https://programmers.co.kr/learn/challenges?tab=all_challenges";
      driver.get(url);
      wait(1);

      // java
      wel = getElementByQuery("#collapseFilterLanguage > li:nth-child(5) > label");
      clicker(wel, 5);
      
      
      
      // level1 page1
      wel = getElementByQuery("#collapseFilterLevel > li:nth-child("+ i + ") > label");
      //#collapseFilterLevel > li:nth-child(2) > label
      clicker(wel, 5);
      TitleList.addAll(getTitle());

//      page2
      wel = getElementByQuery(
            "#tab_all_challenges > section > div > div.challenge__algorithms--index.col-md-8 > div.algorithm-list > div.d-flex.justify-content-center > nav > ul > li:nth-child(3) > a");
      clicker(wel, 10);
      TitleList.addAll(getTitle());

//      
      driver.close();
      return TitleList;
   }

   // 제목 지정
   public static ArrayList<String> getTitle() {
      List<WebElement> welList = getElementsByQuery(".title");
      String name = null;
      ArrayList<String> Title = new ArrayList<>();
//  
      for (WebElement welList1 : welList) {
         name = welList1.getText();
         Title.add(name);
      }
      return Title;
   }

   public static void wait(int second) {
      for (int i = 0; i < second; i++) {

         try {

            Thread.sleep(100);

         } catch (InterruptedException e) {
            e.printStackTrace();

         }
      }

   }

   public static WebElement getElementByQuery(String query) {
      return driver.findElement(By.cssSelector(query));
   }

   public static List<WebElement> getElementsByQuery(String query) {
      return driver.findElements(By.cssSelector(query));// 1
   }


   public static void clicker(WebElement wel, int second) {
      wel.click();
      wait(second);
   }


   public static Elements getElementsByQuery(Document doc, String query) {
      Elements el = doc.select(query);
      return el;
   }


}