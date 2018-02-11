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
public class MarketParserServiceImpl implements MarketService {

    @Autowired
    private MarketDao marketDao;

    public static void main(String[] args) throws IOException {
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setJavascriptEnabled(true);
//        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
//                "D:\\phantomjs\\phantomjs\\bin\\phantomjs.exe");
//        WebDriver driver = new PhantomJSDriver(caps);
//        String fileName = "F://out.txt";
//        Stream<String> stream = Files.lines(Paths.get(fileName));
//        List<String> result = stream.collect(Collectors.toList());
//        int count = 0;
//        String[] strings = null;
//        String urlImage = null;
//        String resultMar = null;
//        stream.close();
//        List<Market> marketList = new ArrayList<>();
//        for (String items : result) {
//            try {
//                Thread.sleep(30000);
//                strings = items.split(";");
//                driver.navigate().to(strings[1]);
//                WebElement iamge = driver.findElement(By.xpath("//div[@class='n-gallery__item']"));
//                WebElement image = iamge.findElement(By.className("n-gallery__image"));
//                urlImage = image.getAttribute("src");
//                resultMar = strings[0] + ";" + urlImage;
//                System.out.println(resultMar);
//                marketList.add(new Market(strings[0], urlImage));
//                count++;
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        PrintWriter pw = new PrintWriter(new FileWriter("F://outResult.txt"));
//        for (Market items : marketList) {
//            pw.write(items.getSku() + ";" + items.getUrl());
//            pw.write("\n");
//        }
//        pw.close();
//        System.out.println("sdg");


//        String fileName = "F://out.txt";
//        Stream<String> stream = Files.lines(Paths.get(fileName));
//        List<String> result = stream.collect(Collectors.toList());
//        System.setProperty("webdriver.gecko.driver",
//                "D:\\geckodriver.exe");
//        WebDriver driver = new FirefoxDriver();
//        List<Market> marketList = new ArrayList<>();
//        for (String items : result) {
//            try {
//                Thread.sleep(30000);
//                String[] strings = items.split(";");
//                while (strings[0].equals("310738"))
//                driver.navigate().to(strings[1]);
//                WebElement iamge = driver.findElement(By.xpath("//div[@class='n-gallery__item']"));
//                WebElement image = iamge.findElement(By.className("n-gallery__image"));
//                String urlImage = image.getAttribute("src");
//                String resultMar = strings[0] + ";" + urlImage;
//                System.out.println(resultMar);
//
//                marketList.add(new Market(strings[0], urlImage));
//                driver.close();
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//
//        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("resultMarket.txt"), "utf-8"));
//        for (Market items : marketList) {
//            String fileText = items.getSku() + ";" + items.getUrl();
//            writer.write(fileText);
//            writer.write("\n");
//        }
//         writer.close();
//
//        driver.close();

        Document doc = Jsoup.connect("https://market.yandex.ru/product/1715265482").get();
        Element masthead = doc.select("div.n-gallery__item").first();
        Elements newsHeadlines = doc.select("#mp-itn b a");
        Elements urlimage = masthead.getElementsByTag("img");
        String result = urlimage.attr("src");
        System.out.println("dgsg");

    }

    public void startParser() throws IOException, InterruptedException {

    }
}
