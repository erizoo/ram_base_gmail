package by.boiko.crm.service.impl;

import by.boiko.crm.model.*;
import by.boiko.crm.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private HSSFWorkbook book;
    private static final String MAIL_STORE_TYPE = "pop.gmail.com";
    private static final String USERNAME = "erizosashka@gmail.com";
    private static final String PASSWORD = "Alex20968";
    private List<Email> emailList = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();
    private List<String> nameItemList = new ArrayList<>();
    private List<String> priceItemList = new ArrayList<>();
    private List<String> amountItemList = new ArrayList<>();
    private List<String> nameItemListTest = new ArrayList<>();
    private List<String> amountItemListTest = new ArrayList<>();
    private List<String> priceItemListTest = new ArrayList<>();
    private String dealByName;
    private Message message;

    @Override
    public List<Category> getAllFromPage(int page) throws IOException {
        int numberOfSheets = book.getNumberOfSheets();
        List<Category> linesCategories = new ArrayList<>();
        if (page == 1) {
            for (int i = 0; i <= page * 10; i++) {
                linesCategories.add(new Category(i, book.getSheetName(i)));
            }
        } else {
            for (int i = page * 10; i <= page * 10 + 10; i++) {
                linesCategories.add(new Category(i, book.getSheetName(i)));
            }
        }
        return linesCategories;
    }

    @Override
    public List<Order> getEmails() throws MessagingException {
        return check(MAIL_STORE_TYPE, MAIL_STORE_TYPE, USERNAME, PASSWORD);
    }

    @Override
    public List<Category> getAll() throws IOException {
        int numberOfSheets = book.getNumberOfSheets();
        List<Category> linesCategories = new ArrayList<>();
        for (int i = 0; i <= numberOfSheets - 1; i++) {
            linesCategories.add(new Category(i, book.getSheetName(i)));
        }
        return linesCategories;
    }

    @Override
    public List<User> getTop(int number) throws IOException {
        List<User> lines = new ArrayList<>();
        HSSFSheet sheet;
        try {
            sheet = book.getSheetAt(number);
        } catch (Exception e) {
            sheet = book.getSheetAt(0);
        }
        int limit = 0;
        for (Row currentRow : sheet) {
            if (limit >= 20) {
                break;
            }
            String nameCategory = currentRow.getCell(0).getStringCellValue();
            String numberPopular = null;
            if (currentRow.getCell(1).getCellTypeEnum() == CellType.STRING) {
                numberPopular = currentRow.getCell(1).getStringCellValue();
            } else if (currentRow.getCell(1).getCellTypeEnum() == CellType.NUMERIC) {
                numberPopular = String.valueOf(currentRow.getCell(1).getNumericCellValue());
            }
            String nameManufacturer;
            try {
                nameManufacturer = currentRow.getCell(2).getStringCellValue();
            } catch (Exception e) {
                nameManufacturer = "null";
            }

            String nameModel = currentRow.getCell(3).getStringCellValue();
            StringBuilder stringBuilder = new StringBuilder(nameModel);
            nameModel = stringBuilder.delete(0, stringBuilder.indexOf(" ") + 1).toString();
            String skuMarketFromExcel;
            String skuMarket;
            try {
                skuMarketFromExcel = currentRow.getCell(4).getStringCellValue();
                String[] item_one = skuMarketFromExcel.split("/");
                String[] item_two = Arrays.toString(new String[]{item_one[4]}).split("hid");
                String item_three = item_two[0];
                skuMarket = item_three.substring(1, item_three.length() - 2);
            } catch (Exception e) {
                skuMarketFromExcel = "null";
                skuMarket = "null";
            }
            String skuRamFromExcel;
            try {
                skuRamFromExcel = currentRow.getCell(5).getStringCellValue();
            } catch (Exception e) {
                skuRamFromExcel = "null";
            }
            String[] item_market = skuRamFromExcel.split("=");
            String skuRam = item_market[item_market.length - 1];
            lines.add(new User(nameCategory, nameManufacturer, nameModel, skuMarket, skuRam, numberPopular, skuMarketFromExcel));
            limit++;
            System.out.println(skuMarketFromExcel);
        }

        return lines;
    }

    @Override
    public int getCount() throws IOException {
        return book.getNumberOfSheets();
    }

    @Override
    public void downloadFile() throws IOException {
        InputStream is = new URL("http://ram.by/test/market_top_skus/pricelabs-popular-models-0.xls").openStream();
        POIFSFileSystem fs = new POIFSFileSystem(is);
        book = new HSSFWorkbook(fs);
        for (int i = 0; i <= book.getNumberOfSheets() - 1; i++) {
            book.getSheetAt(i).removeRow(book.getSheetAt(i).getRow(0));
        }
    }

    private List<Order> check(String host, String mail_store_type, String username, String password) throws MessagingException {
        try {
            orderList.clear();
            emailList.clear();
            //create properties field
            Properties properties = new Properties();

            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");

            store.connect(host, username, password);

            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            if (messages.length == 0){
                orderList.add(new Order("No emails"));
            }else {
                System.out.println("messages.length---" + messages.length);
                Object content = null;
                for (int i = 0, n = messages.length; i < n; i++) {
                    message = messages[i];
                    int emailNumber = i + 1;
                    String emailSubject = message.getSubject();
                    String emailFrom = String.valueOf(message.getFrom()[0]);
                    String[] arrayString = emailFrom.split("<");
//                    StringBuilder stringBuilder = new StringBuilder();
//                    stringBuilder.append(arrayString[1].substring(0, arrayString[1].length() - 1));
//                    emailFrom = String.valueOf(stringBuilder);
                    content = message.getContent();
                    String context = getTextFromMimeMultipart((MimeMultipart) content);
                    emailList.add(new Email(emailNumber, "sdgsd", "dsgsg", context));
                    String lines[] = emailList.get(i).getSubject().split("[\\r\\n]+", -1);
                    if (context.contains("покупатель создал новый чат")){
                        orderList.add(new Order("No emails"));
                    }
                    if (context.contains("Отложенный звонок с сайта")){
                        orderList.add(new Order("Отложенный звонок с сайта", phoneNumberFormatDeferredCall(lines)));
                    }
                    if (context.contains("Здравствуйте, Евгений!")){
                        dealByName = "";
                        for (int y = 0;y <= lines.length - 1; y++){
                            if(lines[y].contains("руб.")){
                                dealByName = lines[y-2].substring(0, lines[y-2].length()-4);
                                break;
                            }
                        }
                        // orderList.add(new Order("Error retrieving data"));
                        orderList.add(new Order(nameToFormatDealBy(lines), phoneNumberFormatDealBy(lines),
                                emailToFormatDealBy(lines), addressToFormatDealBy(lines), orderToFormatDealBy(dealByName, lines), "DEAL.BY"));
                    }if(context.contains("поступил заказ на звонок")){
                        orderList.add(new Order(nameToFormatCall(lines), phoneNumberFormatCall(lines),productToFormatCall(lines), "UNISHOP.BY"));
                    }
                    if (context.contains("поступил новый заказ!")) {
                        orderList.add(new Order(nameToFormat(lines), phoneNumberFormat(lines), emailToFormat(lines),
                                addressToFormat(lines), orderToFormat(lines),"UNISHOP.BY"));
                    }
                }
                emailFolder.close(false);
                store.close();
            }


        } catch (Exception e) {
            orderList.clear();
            orderList.add(new Order("Error retrieving data" + "," + " " + message.getSubject()));
            e.printStackTrace();
            return orderList;
        }
        return orderList;
    }

    private String phoneNumberFormatDeferredCall(String [] lines) {
        try{
            List<String> linesList = Arrays.asList(lines);
            List<String> item = linesList.stream().filter(p -> p.contains("Отложенный звонок с сайта ram.by на номер")).collect(Collectors.toList());
            String str = String.join(", ", item);
            String firstChange = str.substring(42,  str.length()-8);
            String secondShange = firstChange.replaceAll("\\D+", "");
            StringBuffer result = new StringBuffer();
            return String.valueOf(result.append("+375").append(" ").append(secondShange.substring(0,2)).append(" ").append(secondShange.substring(2,secondShange.length())));
        }catch (Exception e){
            return "Error" + e;
        }
    }

    private String nameToFormatCall(String[] line) {
        List<String> lines = Arrays.asList(line);
        List<String> listName = lines.stream().filter(p -> p.contains("Имя покупателя:")).collect(Collectors.toList());
        String listNameString = String.join(", ", listName);
        String items[] = listNameString.split(" ");
        return items[2];
    }

    private String nameToFormatDealBy(String[] line) {
        List<String> lines = Arrays.asList(line);
        List<String> listName = lines.stream().filter(p -> p.contains("ФИО:")).collect(Collectors.toList());
        String listNameString = String.join(", ", listName);
        String items[] = listNameString.split(" ");
        if (items.length > 2){
            return items[1] + " " + items[2];
        }else {
            return items[1] + " None";
        }
    }

    private String nameToFormatDealByMessage(String[] line){
        List<String> lines = Arrays.asList(line);
        List<String> listName = lines.stream().filter(p -> p.contains("Вам писал:")).collect(Collectors.toList());
        String listNameString = String.join(", ", listName);
        String items[] = listNameString.split(" ");
        return items[2];
    }

    private String phoneNumberFormatCall(String[] line) {
        List<String> lines = Arrays.asList(line);
        List<String> listPhone = lines.stream().filter(p -> p.contains("Контактный телефон:")).collect(Collectors.toList());
        String listPhoneString = String.join(", ", listPhone);
        String[] linesItems = listPhoneString.split(" ");
        String[] item = linesItems[5].split("-");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(linesItems[3].substring(1, 4)).append(" ").append(linesItems[4].substring(1, 3)).append(" ").append(item[0]).append(item[1]).append(item[2]);
        return String.valueOf(stringBuilder);
    }

    private String phoneNumberFormatDealBy(String[] line) {
        List<String> lines = Arrays.asList(line);
        List<String> listPhone = lines.stream().filter(p -> p.contains("Телефон:")).collect(Collectors.toList());
        String listPhoneString = String.join(", ", listPhone);
        String[] linesItems = listPhoneString.split(" ");
        StringBuffer result = new StringBuffer();
        return String.valueOf(result.append(linesItems[1].substring(1,4)).append(" ").append(linesItems[1].substring(4,6)).append(" ").append(linesItems[1].substring(6,13)));
    }

    private String productToFormatCall(String[] line) {
        List<String> lines = Arrays.asList(line);
        List<String> list = lines.stream().filter(p -> p.contains("Запрос был сделан со страницы")).collect(Collectors.toList());
        String listString = String.join(", ", list);
        String[] linesItems = listString.split("\"");
        return linesItems[1];
    }

    private String nameToFormat(String[] line) {
        List<String> lines = Arrays.asList(line);
        List<String> list = lines.stream().filter(p -> p.contains("Имя покупателя:")).collect(Collectors.toList());
        String listString = String.join(", ", list);
        String[] linesItems = listString.split(" ");
        if (linesItems.length <= 3) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("None").append(" ").append(linesItems[2]);
            return String.valueOf(stringBuilder);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            return String.valueOf(stringBuilder.append(linesItems[2]).append(" ").append(linesItems[3]));
        }

    }

    private String addressToFormat(String[] line) {
        List<String> lines = Arrays.asList(line);
        List<String> list = lines.stream().filter(p -> p.contains("Адрес доставки:")).collect(Collectors.toList());
        list.remove("Адрес");
        list.remove("доставки:");
        String [] item = list.get(0).split(":");
        return item[1];
    }
    private String addressToFormatDealBy(String[] line) {
        int numberElementArray = 0;
        for (int i = 0; i <= line.length-1; i++){
            if (line[i].equals("Физический адрес:")){
                numberElementArray = i + 1;
                break;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(line[numberElementArray]);
        return String.valueOf(stringBuilder);
    }

    private String emailToFormat(String[] line) {
        List<String> lines = Arrays.asList(line);
        List<String> list = lines.stream().filter(p -> p.contains("Email:")).collect(Collectors.toList());
        String listString = String.join(", ", list);
        String[] linesItems = listString.split(" ");
        System.out.println(linesItems[1]);
        return linesItems[1];
    }

    private String emailToFormatDealBy(String[] line) {
        List<String> lines = Arrays.asList(line);
        List<String> list = lines.stream().filter(p -> p.contains("Email:")).collect(Collectors.toList());
        String listString = String.join(", ", list);
        String[] itemsString = listString.split(" ");
        return itemsString[1];
    }

    private List<Product> orderToFormatDealBy(String line, String[] lines) {
        nameItemList.clear();
        amountItemList.clear();
        priceItemList.clear();
        String[] orderItems =  line.split(" ");
        List<String> linesList = Arrays.asList(lines);
        List<Product> productList = new ArrayList<>();
        productList.clear();
        List<String> list = linesList.stream().filter(p -> p.contains("руб.")).collect(Collectors.toList());
        for (int i = 0; i <= list.size()-1; i++){
            System.out.println(list.get(i));
        }
        if (list.size() > 2){
            for (int i = 0;i <= lines.length - 1; i++){
                if(lines[i].contains("руб.")){
                    nameItemList.add(lines[i-3]);
                }
            }
            for (int k = 0; k < list.size()-1; k++ ){
                String str = list.get(k);
                String[] items = str.split(" ");
                if (items.length > 8){
                    String price = items[0] + "" + items[1];
                    productList.add(new Product(nameItemList.get(k),price, items[4]));
                }else {
                    productList.add(new Product(nameItemList.get(k),items[0], items[3]));
                }
            }
        }else{
            List<String> linesListItem = Arrays.asList(lines);
            List<String> str = linesListItem.stream().filter(p -> p.contains("руб.")).collect(Collectors.toList());
            String[] strItems = String.valueOf(str).split(" ");
            if (strItems.length > 8){
                String price = strItems[0] + "" + strItems[1];
                nameItemList.add(line);
                amountItemList.add( strItems[3]);
                priceItemList.add(price.substring(1, price.length()-4));
                productList.add(new Product(nameItemList.get(0),priceItemList.get(0), amountItemList.get(0)));
            }else {
                nameItemList.add(line);
                amountItemList.add(strItems[3]);
                priceItemList.add(strItems[0].substring(1, strItems[0].length()));
                productList.add(new Product(nameItemList.get(0),priceItemList.get(0), amountItemList.get(0)));
            }
        }
        return productList;
    }

    private List<Product> orderToFormat(String[] line) {
        List<String> lines = Arrays.asList(line);
        List<String> list = lines.stream().filter(p -> p.contains("1.") | p.contains("2.") | p.contains("3.") | p.contains("4.")).collect(Collectors.toList());
        String[] check = String.valueOf(list).split("//");
        List<Product> productList = new ArrayList<>();
        if (check.length > 3) {
            String[] items = String.valueOf(list).split(",");
            for (int i = 0; i <= items.length - 1; i++) {
                String[] firstItem = items[i].split("//");
                nameItemList.add(firstItem[0]);
                amountItemList.add(firstItem[1]);
                priceItemList.add(firstItem[2]);
            }
            for (int i = 0; i <= items.length - 1; i++) {
                String[] priceString = priceItemList.get(i).split(" ");
                priceItemListTest.add(priceString[2]);
                String[] amountString = amountItemList.get(i).split(" ");
                amountItemListTest.add(amountString[2]);
                String[] nameString = nameItemList.get(i).split(",");
                String[] nameItem = nameString[0].split("\\.");
                nameItemListTest.add(nameItem[1]);

                productList.add(new Product(nameItemListTest.get(i), priceItemListTest.get(i), amountItemListTest.get(i)));
            }
        } else {
            String[] items = String.valueOf(list).split("//");
            String nameItem = items[0].substring(4, items[0].length() - 1);
            String[] amountItem = items[1].split(" ");
            String amountItemTest = amountItem[2];
            String[] priceItem = items[2].split(" ");
            productList.add(new Product(nameItem, priceItem[2], amountItemTest));
        }

        return productList;
    }

    private String phoneNumberFormat(String[] line) {
        List<String> lines = Arrays.asList(line);
        List<String> list = lines.stream().filter(p -> p.contains("Контактный телефон:")).collect(Collectors.toList());
        String listString = String.join(", ", list);
        String[] linesItems = listString.split(" ");
        String[] item = linesItems[4].split("-");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(linesItems[2].substring(1, 4)).append(" ").append(linesItems[3].substring(1, 3)).append(" ").append(item[0]).append(item[1]).append(item[2]);
        System.out.println(stringBuilder);
        return String.valueOf(stringBuilder);
    }

    private String dateToFormat(String line) {
        String[] lines = line.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(lines[1]).append("/").append("0").append(LocalDate.now().getMonth().getValue()).append("/").append(lines[3]).append(" ").append(lines[5]);
        System.out.println(stringBuilder);
        return String.valueOf(stringBuilder);
    }

    private String getTextFromMessage(Message message) throws MessagingException, IOException {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    private String getTextFromMimeMultipart(
            MimeMultipart mimeMultipart) throws MessagingException, IOException {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append("\n").append(bodyPart.getContent());
                break; // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result.append("\n").append(org.jsoup.Jsoup.parse(html).text());
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
            }
        }
        return result.toString();
    }
}
