package by.boiko.crm.service.impl;

import by.boiko.crm.dao.MarketDao;
import by.boiko.crm.model.Market;
import by.boiko.crm.service.MarketService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Erizo on 29.01.2018.
 */
@Service
@Transactional
public class MarketParserServiceImpl {

    @Autowired
    private MarketDao marketDao;

    public static void main(String[] args) throws IOException, InterruptedException {
        String fileName = "videoMarket.txt";
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("resultMarketVideoWithUrl.txt"), "utf-8"));
        Stream<String> stream = Files.lines(Paths.get(fileName));
        List<String> result = stream.collect(Collectors.toList());
        System.setProperty("webdriver.gecko.driver",
                "D:\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        for (String items : result) {
            Thread.sleep(5000);
            String[] strings = items.split(";");
            driver.navigate().to(strings[1]);
            try {
                WebElement image = driver.findElement(By.xpath("//iframe[@class='sandbox']"));
                String urlVideo = image.getAttribute("src");
                String[] strs = urlVideo.split("youtube.com");
                String[] strings1 = strs[1].split("%3F");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("www.youtube.com").append(strings1[0].replaceAll("%2F", "/"));
                System.out.println(strings[0] + ";" + stringBuilder);
                writer.write(strings[0] + ";" + stringBuilder);
                writer.write("\n");
                writer.flush();
            } catch (Exception e){
                System.out.println(strings[0] + ";" + "нету");
                writer.flush();
            }

        }
//        String fileName = "C:\\Users\\Erizo\\Downloads\\Telegram Desktop\\out.txt";
//        DesiredCapabilities caps = new DesiredCapabilities();
//        System.setProperty("webdriver.gecko.driver",
//                "D:\\geckodriver.exe");
//        WebDriver driver = new FirefoxDriver();
//        driver.navigate().to("https://market.yandex.by/product/13811586");
//        WebElement image = driver.findElement(By.xpath("//iframe[@class='sandbox']"));
//        String urlVideo= image.getAttribute("src");
//        String str = "https://yastatic.net/video-player/0xf7edcf0/pages-common/youtube/youtube.html#html=%3Ciframe%20src%3D%22%2F%2Fwww.youtube.com%2Fembed%2FQ4zQJmw3ZoA%3Fenablejsapi%3D1%26amp%3Bwmode%3Dopaque%22%20frameborder%3D%220%22%20scrolling%3D%22no%22%20allowfullscreen%3D%221%22%20aria-label%3D%22Video%22%3E%3C%2Fiframe%3E";
//        String[] strings = str.split("youtube.com");
//        String[] strings1 = strings[1].split("%3F");
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("www.youtube.com").append(strings1[0].replaceAll("%2F", "/"));
//        System.out.println("sdg");

//        driver.close();
//        Stream<String> stream = Files.lines(Paths.get(fileName));
//        List<String> result = stream.collect(Collectors.toList());
//        List<Market> marketList = new ArrayList<>();
//        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("resultMarket.txt"), "utf-8"));
//        for (String items : result) {
//            try {
//                Thread.sleep(2000);
//                String[] strings = items.split(";");
//                driver.navigate().to(strings[1]);
//                WebElement iamge = driver.findElement(By.xpath("//div[@class='n-gallery__item']"));
//                WebElement image = iamge.findElement(By.className("n-gallery__image"));
//                String urlImage = image.getAttribute("src");
//                String resultMar = strings[0] + ";" + urlImage;
//                System.out.println(resultMar);
//                marketList.add(new Market(strings[0], urlImage));
//                String fileText = strings[0] + ";" + urlImage;
//                writer.write(fileText);
//                writer.write("\n");
//                writer.flush();
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        writer.close();

    }

}
