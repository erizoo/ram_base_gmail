package by.boiko.crm.service.impl.ParserMail;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MigomParser {

    public Migom parser(String[] lines) {
        Migom migom = new Migom();
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("Заказ №")){
                String[] strings = lines[i].split("href");
                String[] str = strings[1].split(">");
                String result = str[0].substring(2).replace("\"", "");
                Document doc = null;
                Elements name = null;
                try {
                    doc = Jsoup.connect(result).get();
                    name = doc.select("span.m-r-10");
                    System.out.println();
                    result = name.text();
                } catch (IOException e) {
                }
                migom.setUrl(result);
                System.out.println();
            }
            if (lines[i].contains("tel:")) {
                String[] strings = lines[i].split(">");
                String result = strings[1].substring(4, strings[1].length() - 3);
                StringBuilder sb = new StringBuilder(result);
                sb.insert(2," ");
                migom.setPhoneNumber(sb.toString());
            }
            if (lines[i].contains("Покупатель")) {
                String str = lines[i + 6].replaceAll("[^А-Яа-я]", "");
                migom.setName(str);
            }
        }

        return migom;
    }
}
