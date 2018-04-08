package by.boiko.crm.service.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RozetkaMain {

    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver",
                "D:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String param = null;
        String value = null;
        driver.navigate().to("https://hard.rozetka.com.ua/gigabyte_b360_aorus_gaming_3_wifi/p36867696/comments/#tab=characteristics");
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\test.txt"), "utf-8"));
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = currentTime.format(formatter);
        writer.write("<!-- boiko-" + formatDateTime + "-->");
        writer.write("\n");
        writer.write("<table>");
        WebElement table = driver.findElement(By.xpath("//table[@class='chars-t']"));
        WebElement tbody = table.findElement(By.xpath("//tbody"));
        List<WebElement> tr = tbody.findElements(By.xpath("//tr"));
        for (WebElement item : tr) {
            String[]str =  item.getText().split("\n");
            param = str[0];
            value = str[1];
            writer.write("<tr>\n" +
                    "<td class=\"param-name\">" + param + "</td>\n" +
                    "<td>" + value + "</td>\n" +
                    "</tr>");
        }
        writer.write("</table>");
        writer.flush();
    }
}
