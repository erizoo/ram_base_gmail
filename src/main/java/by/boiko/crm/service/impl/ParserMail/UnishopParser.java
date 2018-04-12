package by.boiko.crm.service.impl.ParserMail;

import by.boiko.crm.model.ItemsOrder;

import java.util.ArrayList;
import java.util.List;

public class UnishopParser {

    private List<ItemsOrder> list = new ArrayList<>();
    private List<String> listName = new ArrayList<>();
    private List<String> listAmount = new ArrayList<>();
    private List<String> listPrice = new ArrayList<>();
    boolean check = false;

    public Unishop parser(String[] lines) {
        Unishop unishop = new Unishop();
        for (String items : lines) {
            if (items.contains("Имя покупателя")) {
                unishop.setName(items.substring(16, items.length()));
            }
            if (items.contains("Контактный телефон:")) {
                StringBuilder stringBuilder = new StringBuilder();
                String str = items.substring(25, items.length());
                String[] strings = str.split(" ");
                stringBuilder.append(strings[0].substring(1, 3)).append(" ").append(strings[1].replaceAll("-", ""));
                unishop.setPhoneNumber(String.valueOf(stringBuilder));
            }
            if (items.contains("Email:")) {
                unishop.setEmail(items.substring(7, items.length()));
            }
            if (items.contains("Адрес доставки:")) {
                unishop.setAddress(items.substring(16, items.length()));
            }
        }
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("---------") && !check) {
                String[] strings = lines[i + 1].split("/");
                listName.add(strings[0].trim());
                listAmount.add("1");
                listPrice.add(strings[1].substring(0, strings[1].length()-5));
                check = true;
            }
        }
        for (int i = 0; i < listAmount.size(); i++) {
            list.add(new ItemsOrder(listName.get(i), listAmount.get(i), listPrice.get(i)));
            unishop.setListOrder(list);
        }
        System.out.println();
        return unishop;
    }

    public Unishop parserCall(String[] lines){
        Unishop unishop = new Unishop();
        for (String items : lines) {
            if (items.contains("Имя покупателя")) {
                unishop.setName(items.substring(16, items.length()));
            }
            if (items.contains("Контактный телефон:")) {
                StringBuilder stringBuilder = new StringBuilder();
                String str = items.substring(25, items.length());
                String[] strings = str.split(" ");
                stringBuilder.append(strings[1].substring(1, 3)).append(" ").append(strings[2].replaceAll("-", ""));
                unishop.setPhoneNumber(String.valueOf(stringBuilder));
            }
        }
        unishop.setNotes("Заказ звонка");
        return unishop;
    }

}
