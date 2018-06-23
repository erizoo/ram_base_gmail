package by.boiko.crm.service.impl;

import com.google.gson.Gson;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Erizo on 23.06.2018.
 */
public class YandexMarketImage {

    private static final String MARKET_PRODUCT_URL = "https://market.yandex.by/product/";

    public static void main(String[] args) throws IOException {

        String fileName = "market.txt";
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("resultMarketImageWithUrl.txt"), "utf-8"));
        Stream<String> stream = Files.lines(Paths.get(fileName));
        List<String> result = stream.collect(Collectors.toList());
        System.setProperty("webdriver.gecko.driver",
                "D:\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        List<String> urlList;
        for (String items : result) {
            String[] strings = items.split(",");
            String skuProduct = strings[1].replaceAll("\"", "");
            driver.navigate().to(MARKET_PRODUCT_URL + skuProduct + "#gallery");
            try {
                urlList = new ArrayList<>();
                WebElement imageContainer = driver.findElement(By.xpath("//ul[@class='n-gallery__thumbs']"));
                List<WebElement> images = imageContainer.findElements(By.xpath("//li[@class='n-gallery-popup__thumbs-item']"));
                for (WebElement urlItems : images) {
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    executor.executeScript("arguments[0].click();", urlItems);
                    WebElement image = driver.findElement(By.xpath("//div[@class='n-gallery-popup__item-wrap n-gallery-popup__item-wrap_active_yes']"));
                    String img = image.findElement(By.tagName("img")).getAttribute("src");
                    urlList.add(img);
                }
                writer.write(new Gson().toJson(new YandexMarketImageModel(strings[0], urlList)));
                writer.write(",");
                writer.flush();
            } catch (Exception e){
                try {
                    urlList = new ArrayList<>();
                    driver.navigate().to(MARKET_PRODUCT_URL + skuProduct);
                    WebElement webElement = driver.findElement(By.xpath("//div[@class='n-gallery__item']"));
                    String img = webElement.findElement(By.tagName("img")).getAttribute("src");
                    urlList.add(img);
                    writer.write(new Gson().toJson(new YandexMarketImageModel(strings[0], urlList)));
                    writer.write(",");
                    writer.flush();
                } catch (Exception message){
                    urlList = new ArrayList<>();
                    urlList.add("");
                    writer.write(new Gson().toJson(new YandexMarketImageModel(strings[0], urlList)));
                    writer.write(",");
                    writer.flush();
                }

            }
            System.out.println("dsg");
//            WebElement image = driver.findElement(By.xpath("//div[@class='n-gallery__item']"));
//            WebElement imageClass = image.findElement(By.xpath("//img[@class='n-gallery__image image']"));
//            String imageUrl = imageClass.getAttribute("src");
        }
    }
}
