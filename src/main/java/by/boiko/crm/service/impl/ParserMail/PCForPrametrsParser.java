package by.boiko.crm.service.impl.ParserMail;

import by.boiko.crm.model.ItemsOrder;

import java.util.ArrayList;
import java.util.List;

public class PCForPrametrsParser {

    private List<ItemsOrder> list = new ArrayList<>();
    private List<String> listSku = new ArrayList<>();
    private List<String> listAmount = new ArrayList<>();
    private List<String> listPrice = new ArrayList<>();

    public PCForPrametrs parser(String[] lines) {
        PCForPrametrs pcForPrametrs = new PCForPrametrs();
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("Имя")){
                String[] str = lines[i + 2].split(">");
                pcForPrametrs.setName(str[1].substring(0, str[1].length()-4));
            } if (lines[i].contains("Мобильный")){
                String[] str = lines[i + 2].split(">");
                pcForPrametrs.setPhoneNumber(str[1].substring(4, str[1].length()-4));
                System.out.println("dsgg");
            } if (lines[i].contains("Процессор")){
                String[] str = lines[i + 3].split(">");
                listSku.add("Процессор" + " " + str[2]);
                listAmount.add("1");
                listPrice.add(lines[i + 6].trim());
                System.out.println("sdg");
            } if (lines[i].contains("Кулер")){
                listSku.add("Кулер");
                listAmount.add("1");
                listPrice.add(lines[i + 6].trim());
            } if (lines[i].contains("Материнская плата")){
                listSku.add("Материнская плата");
                listAmount.add("1");
                listPrice.add(lines[i + 6].trim());
            } if (lines[i].contains("Видеокарта")){
                listSku.add("Видеокарта" + " " + lines[i + 3].trim());
                listAmount.add("1");
                listPrice.add(lines[i + 6].trim());
            } if (lines[i].contains("Оперативная память")){
                listSku.add("Оперативная память" + " " + lines[i + 3].trim());
                listAmount.add("1");
                listPrice.add(lines[i + 6].trim());
            } if (lines[i].contains("Жесткий диск")){
                listSku.add("Жесткий диск" + " " + lines[i + 3].trim());
                listAmount.add("1");
                listPrice.add(lines[i + 6].trim());
            } if (lines[i].contains("SSD диск")){
                listSku.add("SSD диск" + " " + lines[i + 3].trim());
                listAmount.add("1");
                listPrice.add(lines[i + 6].trim());
            } if (lines[i].contains("Блок питания")){
                listSku.add("Блок питания" + " " + lines[i + 3].trim());
                listAmount.add("1");
                listPrice.add(lines[i + 6].trim());
            } if (lines[i].contains("Корпус")){
                listSku.add("Корпус" + " " + lines[i + 3].trim());
                listAmount.add("1");
                listPrice.add(lines[i + 6].trim());
            } if (lines[i].contains("Оптический накопитель")){
                listSku.add("Оптический накопитель" + " " + lines[i + 3].trim());
                listAmount.add("1");
                listPrice.add(lines[i + 6].trim());
            }
        }
        for (int i = 0; i < listSku.size(); i++) {
            list.add(new ItemsOrder(listSku.get(i), listAmount.get(i), listPrice.get(i)));
            pcForPrametrs.setListOrder(list);
        }
        System.out.println("sdgg");
        return pcForPrametrs;
    }
}
