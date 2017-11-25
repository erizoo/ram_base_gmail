package by.boiko.crm.service.impl;

import by.boiko.crm.model.Onliner;
import by.boiko.crm.model.Table;
import by.boiko.crm.service.OnlinerService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OnlinerServiceImpl implements OnlinerService {


    public static void takeNamesAndLinks() {
        WebDriver driver;
        System.setProperty("webdriver.gecko.driver", "D:\\Projects\\chromedriver.exe ");
        driver = new FirefoxDriver();
        driver.navigate().to("https://catalog.onliner.by/videocard");

        List<WebElement> allAuthors = driver.findElements(By.className("schema-product__title"));
        for (WebElement str : allAuthors) {
            String name = str.getText();
        }
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='schema-product__title']/*[@href]"));
        for (WebElement element : list) {
            String link = element.getAttribute("href");
            System.out.println(element.getTagName() + "=" + link + ", " + element.getText());
        }

        List<WebElement> allTitles = driver.findElements(By.className("tgProductTitleText"));
        driver.close();
    }

    @Override
    public List<Onliner> getReviews() {
        WebDriver driver;
        System.setProperty("webdriver.gecko.driver", "D:\\Projects\\chromedriver.exe ");
        driver = new FirefoxDriver();
        driver.navigate().to("https://catalog.onliner.by/videocard/palit/ne5105ts18g11071/reviews");
        List<Onliner> listReviews = new ArrayList<>();
        List<String> listStarsForReviews = new ArrayList<>();
        List<String> listTextForReviews = new ArrayList<>();
        List<String> listTextForPlusReviews = new ArrayList<>();
        List<String> listTextForMinusReviews = new ArrayList<>();
        String stars = null;

        List<WebElement> list = driver.findElements(By.xpath("//div[@class='rev-rating']/img[1]"));
        List<WebElement> listReviewsText = driver.findElements(By.className("rev-content"));
        List<WebElement> listPlusReviewsText = driver.findElements(By.xpath("//div[@class='revpc pros']"));
        List<WebElement> listMinusReviewsText = driver.findElements(By.xpath("//div[@class='revpc cons']"));
        for (WebElement element : list) {
            String link = element.getAttribute("src");
            System.out.println(element.getTagName() + "=" + link + ", " + element.getText());
            if (link.contains("_4")) {
                stars = "4";
            }
            if (link.contains("_5")) {
                stars = "5";
            }
            if (link.contains("_3")) {
                stars = "3";
            }
            if (link.contains("_2")) {
                stars = "2";
            }
            if (link.contains("_1")) {
                stars = "1";
            }
            listStarsForReviews.add(stars);
        }
        for (WebElement str : listReviewsText) {
            String name = str.getText();
            listTextForReviews.add(name);
        }
        for (WebElement str : listPlusReviewsText) {
            String name = str.getText();
            listTextForPlusReviews.add(name);
        }
        for (WebElement str : listMinusReviewsText) {
            String name = str.getText();
            listTextForMinusReviews.add(name);
        }
        for (int i = 0; i <= listStarsForReviews.size() - 1; i++) {
            listReviews.add(new Onliner(listStarsForReviews.get(i), listTextForReviews.get(i), listTextForPlusReviews.get(i), listTextForMinusReviews.get(i)));
        }

        driver.close();
        return listReviews;
    }

    @Override
    public ArrayList<Table> getDescription() {
        WebDriver driver;
        System.setProperty("webdriver.gecko.driver", "D:\\Projects\\chromedriver.exe ");
        driver = new FirefoxDriver();
        driver.navigate().to("https://catalog.onliner.by/videocard/palit/ne5105ts18g11071");

        List<WebElement> listReviewsText = driver.findElements(By.cssSelector("table.product-specs__table"));
        ArrayList<String> listCategories = new ArrayList<>();
        ArrayList<Table> listTable = new ArrayList<>();
        List<Table.TypeTrTable> listTableTr = null;
        int i = 0;
        for (WebElement list : listReviewsText) {
            List<WebElement> TBodyCollection = list.findElements(By.tagName("tbody"));
            for (WebElement tbody : TBodyCollection) {
                String[] tbodyItem = tbody.getText().split("\\n");
                listCategories.add(tbodyItem[0]);
                List<WebElement> TRCollection = tbody.findElements(By.tagName("tr"));
                listTableTr = new ArrayList<>();
                for (WebElement tr : TRCollection) {
                    if (tr.getText().contains("\n")) {
                        String[] item = tr.getText().split("\n");
                        ArrayList<String> listParameters = new ArrayList<>();
                        ArrayList<String> listValues = new ArrayList<>();
                        listParameters.add(item[0]);
                        listValues.add(item[1]);
                        listTableTr.add(new Table.TypeTrTable(listParameters.get(0), listValues.get(0)));
                    }
                }
                listTable.add(new Table(listCategories.get(i), listTableTr));
                i++;
            }


        }
        return listTable;
    }

    @Override
    public List<String> getImages() {
        WebDriver driver;
        System.setProperty("webdriver.gecko.driver", "D:\\Projects\\chromedriver.exe ");
        driver = new FirefoxDriver();
        driver.navigate().to("https://catalog.onliner.by/videocard/palit/ne5105ts18g11071");

        List<String> stringList = new ArrayList<>();
        List<WebElement> listImages = driver.findElements(By.xpath("//div[@class='product-gallery__shaft']"));
        for (WebElement list : listImages) {
            List<WebElement> link = list.findElements(By.className("product-gallery__thumb"));
            for (WebElement listImag : link) {
                String linkImage = listImag.getAttribute("data-original");
                stringList.add(linkImage);
            }
        }
        driver.close();
        return stringList;
    }

    @Override
    public List<String> getNames(String name) {
        WebDriver driver;
        System.setProperty("webdriver.gecko.driver", "D:\\Projects\\chromedriver.exe ");
        driver = new FirefoxDriver();
        driver.navigate().to("https://catalog.onliner.by/videocard");
        List<String> listNames = new ArrayList<>();
        List<WebElement> allAuthors = driver.findElements(By.className("schema-product__title"));
        for (WebElement str : allAuthors) {
            String title = str.getText();
            listNames.add(title);
        }

        driver.close();
        return listNames;
    }

    public static void main(String[] args) throws IOException {
        WebDriver driver;
        System.setProperty("webdriver.gecko.driver", "D:\\Projects\\chromedriver.exe ");
        driver = new FirefoxDriver();
        driver.navigate().to("https://catalog.onliner.by/videocard?page=2");
        List<String> listNames = new ArrayList<>();
        List<String> result = new ArrayList<>();
        List<WebElement> allAuthors = driver.findElements(By.className("schema-product__title"));
        int count = 0;
        int min = 0;
        for (WebElement str : allAuthors) {
            String title = str.getText();
            listNames.add(title);
        }
        for (String list : listNames) {
            if (list.contains("NE5105TS18G1-1071D")){
                result.add(list);
            }
//            String[] items = list.split(" ");
//            String[] itemsNamesFromOnliner = "Видеокарта GeForce® Palit GTX1050Ti DUAL OC (NE5105TS18G1-1071D) 4Gb".split(" ");
//            if (itemsNamesFromOnliner.length <= items.length){
//                min = itemsNamesFromOnliner.length;
//                for (int i = 0; i <= itemsNamesFromOnliner.length-1; i++){
//                   for (int k = 0; k <= itemsNamesFromOnliner.length-1; k++){
//                       if (itemsNamesFromOnliner[i].contains(items[k])){
//                           count = count + 1;
//                       }
//                   }
//                }
//            }else {
//                min = items.length;
//                for (int i = 0; i <= items.length-1; i++){
//                    for (int k = 0; k <= items.length-1; k++){
//                        if (items[i].contains(itemsNamesFromOnliner[k])){
//                            count = count + 1;
//                        }
//                    }
//                }
//            }if (count >= min/1.5){
//                result.add(list);
//            }

        }
        driver.close();

    }

}
