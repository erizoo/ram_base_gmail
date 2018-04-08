package by.boiko.crm.service.impl;


import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    //    public static void main(String[] argv) throws IOException {
////
////        String fileName = "D:\\бойко.txt";
////        BufferedImage image = null;
////        Stream<String> stream = Files.lines(Paths.get(fileName));
////        List<String> result = stream.collect(Collectors.toList());
////        int count = 0;
////        for (String items : result) {
////            Files.createDirectories(Paths.get("D:\\foto\\" + items));
////            count++;
////            if (count == 1500){
////                break;
////            }
////        }
////
////    }
    public static void main(String[] args) throws IOException, InterruptedException {
        String fileName = "test.txt";
        String[] strings = null;
        Stream<String> stream = Files.lines(Paths.get(fileName));
        List<String> result = stream.collect(Collectors.toList());
        System.setProperty("webdriver.chrome.driver",
                "D:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
//        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("error.txt"), "utf-8"));
        FileWriter fw = new FileWriter("error.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            for (String items : result) {
                Thread.sleep(5000);
                strings = items.split(";");
                String str = strings[1];
                String str3 = str.replaceAll("[а-яА-Я]*", "");
                String url = "https://www.google.by/search?q=" + str3 + "&source=lnms&tbm=isch&sa=X";
                driver.navigate().to(url);

                WebElement webElement = driver.findElement(By.xpath("//img[@class='rg_ic rg_i']"));
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", webElement);
                WebElement img = driver.findElement(By.xpath("//div[@class='irc_land irc_bg']"));
                WebElement imgURL = img.findElement(By.xpath("//div[@id='Rd1Chf']"));
                WebElement divIRC = imgURL.findElement(By.xpath("//div[@id='irc_cc']"));
                WebElement divIRC2 = divIRC.findElement(By.xpath("//div[@class='irc_c i8187 immersive-container']"));
                WebElement divIRC3 = divIRC2.findElement(By.xpath("//div[@class='irc_t i30052']"));
                List<WebElement> divIRC4 = divIRC3.findElements(By.xpath("//div"));
                List<WebElement> divIRC5 = divIRC4.get(4).findElements(By.xpath("//a"));
                WebElement divIRC6 = divIRC5.get(0).findElement(By.xpath("//img[@class='irc_mi']"));
                String urlSTR = divIRC6.getAttribute("src");
                System.out.println("sdgs");

                InputStream in = new URL(urlSTR).openStream();
                Path path = Paths.get("D:\\parser\\" + strings[0]);
                Files.createDirectories(path);
                Files.copy(in, Paths.get("D:/parser/"+ strings[0] + "/0.jpg"));

            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            assert strings != null;
            bw.write(strings[0]);
            bw.write("\n");
            bw.flush();
        } finally {
            System.out.println("next");
        }


    }

}

