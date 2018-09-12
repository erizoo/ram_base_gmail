package by.boiko.crm.service.impl.ParserMail;

import by.boiko.crm.model.ItemsOrder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConfiguratorParser {

    private List<ItemsOrder> list = new ArrayList<>();
    private List<String> listSku = new ArrayList<>();
    private List<String> listAmount = new ArrayList<>();
    private List<String> listPrice = new ArrayList<>();

    public Configurator parser(String[] lines) {
        Configurator configurator = new Configurator();
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].equals("ФИО")) {
                configurator.setName(lines[i + 1]);
            }
            if (lines[i].equals("Мобильный")) {
                configurator.setPhoneNumber(lines[i + 1]);
            }
            if (lines[i].equals("Адрес")) {
                configurator.setAddress(lines[i + 1]);
            }
            String regex = "(?<!\\d)\\d{6}(?!\\d)";
            String regexFive = "(?<!\\d)\\d{5}(?!\\d)";
            Matcher m = Pattern.compile(regex).matcher(lines[i]);
            Matcher mFive = Pattern.compile(regexFive).matcher(lines[i]);
            if (m.find() && lines[i].length() == 6) {
                listSku.add((m.group(0).trim().substring(0, 6)));
            }
            if (mFive.find() && lines[i].length() == 5) {
                listSku.add((mFive.group(0).trim().substring(0, 5)));
            }
            if (lines[i].contains("шт")) {
                listAmount.add(lines[i].substring(0, 1));
            }
            if (lines[i].contains("руб")) {
                listPrice.add(lines[i]);
            }
            if (lines[i].contains("Сумма заказа")) {
                configurator.setNotes(lines[i]);
            }
        }
        for (int i = 0; i < listPrice.size(); i++) {
            list.add(new ItemsOrder(listSku.get(i), listAmount.get(i), listPrice.get(i)));
            configurator.setListOrder(list);
        }
        return configurator;
    }

}
