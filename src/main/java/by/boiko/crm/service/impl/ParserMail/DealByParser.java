package by.boiko.crm.service.impl.ParserMail;

import by.boiko.crm.model.ItemsOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erizo on 11.02.2018.
 */
public class DealByParser {

    private List<ItemsOrder> list = new ArrayList<>();
    private List<String> listName = new ArrayList<>();
    private List<String> listAmount = new ArrayList<>();
    private List<String> listPrice = new ArrayList<>();

    public DealBy parser(String[] lines) {
        DealBy dealBy = new DealBy();

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].equals("1")) {
                String[] strings = lines[i + 2].split("]");
                listName.add(strings[0].substring(1, strings[0].length()));
            }
            if (lines[i].equals("2")) {
                String[] strings = lines[i + 2].split("]");
                listName.add(strings[0].substring(1, strings[0].length()));
            }
            if (lines[i].equals("3")) {
                String[] strings = lines[i + 2].split("]");
                listName.add(strings[0].substring(1, strings[0].length()));
            }
            if (lines[i].equals("4")) {
                String[] strings = lines[i + 2].split("]");
                listName.add(strings[0].substring(1, strings[0].length()));
            }
            if (lines[i].equals("5")) {
                String[] strings = lines[i + 2].split("]");
                listName.add(strings[0].substring(1, strings[0].length()));
            }
            if (lines[i].contains("руб. x")) {
                String[] strings = lines[i].split("=");
                String[] str = strings[0].split("x");
                String[] strings1 = str[1].split(" ");
                listPrice.add(strings[1].trim());
                listAmount.add(strings1[1]);
            }
            if (lines[i].contains("ФИО:")) {
                dealBy.setName(lines[i + 1]);
            }
            if (lines[i].contains("Email:")) {
                dealBy.setEmail(lines[i + 1].substring(1, lines[i + 1].length() - 5));
            }
            if (lines[i].contains("Телефон:")) {
                String[] strings = lines[i + 1].split("]");
                dealBy.setPhoneNumber(strings[0].substring(5, strings[0].length()));
            }
            if (lines[i].contains("Адрес:")) {
                dealBy.setAddress(lines[i + 1]);
            }
        }
        for (int i = 0; i < listAmount.size(); i++) {
            list.add(new ItemsOrder(listName.get(i), listAmount.get(i), listPrice.get(i)));
            dealBy.setListOrder(list);
        }
        return dealBy;
    }
}
