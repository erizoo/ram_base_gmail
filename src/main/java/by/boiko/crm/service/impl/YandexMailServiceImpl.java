package by.boiko.crm.service.impl;

import by.boiko.crm.model.Order;
import by.boiko.crm.model.Parser;
import by.boiko.crm.service.YandexMailService;
import by.boiko.crm.service.impl.ParserMail.*;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class YandexMailServiceImpl implements YandexMailService {

    private static final String HOST = "pop.yandex.ru";
    private static final String USERNAME = "ram.misnk@yandex.by";
    private static final String PASSWORD = "Alex20968";
    private static final String MAIL_STORE_TYPE = "pop3";
    private static final String MAIL_PORT = "995";
    private static final String TYPE_EMAIL = "INBOX";
    private static final String TYPE_STORE = "pop3s";
    private List<Parser> orderList = new ArrayList<>();

    public List<Parser> check() throws IOException {
        orderList.clear();
        try {
            Message message;
            Properties properties = new Properties();
            properties.put("mail.pop3.host", HOST);
            properties.put("mail.pop3.port", MAIL_PORT);
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore(TYPE_STORE);
            store.connect(HOST, USERNAME, PASSWORD);
            Folder emailFolder = store.getFolder(TYPE_EMAIL);
            emailFolder.open(Folder.READ_WRITE);
            Message[] messages = emailFolder.getMessages();
            emailFolder.setFlags(messages, new Flags(Flags.Flag.SEEN), true);
            if (messages.length == 0) {
                orderList.add(new Parser("No emails"));
                System.out.println("No emails");
            } else {
                System.out.println("messages.length---" + messages.length);
                Object content = null;
                for (Message items : messages) {
                    content = items.getContent();
                    String context = null;
                    if (content instanceof String) {
                        context = (String) content;
                    } else if (content instanceof Multipart) {
                        Multipart mp = (Multipart) content;
                        context = getTextFromMimeMultipart((MimeMultipart) mp);
                    }
                    assert context != null;
                    String[] linesEmail = context.split("[\\r\\n]+", -1);
                    if (items.getFrom()[0].toString().contains("Configurator")){
                        ConfiguratorParser configuratorParser = new ConfiguratorParser();
                        Configurator configurator = configuratorParser.parser(linesEmail);
                        orderList.add(new Parser(configurator.getName(), configurator.getPhoneNumber(), configurator.getAddress(), configurator.getNotes(), configurator.getListOrder(), "Конфигуратор"));
                    }
                    if (context.contains("поступил заказ на звонок")){
                        UnishopParser unishopParser = new UnishopParser();
                        Unishop unishop = unishopParser.parserCall(linesEmail);
                        orderList.add(new Parser(unishop.getName(), unishop.getPhoneNumber(), "Заказ на звонок UNISHOP"));
                    }
                    if (context.contains("В ваш магазин \"Ram.by\" поступил новый заказ!")){
                        UnishopParser unishopParser = new UnishopParser();
                        Unishop unishop = unishopParser.parser(linesEmail);
                        orderList.add(new Parser(unishop.getName(), unishop.getPhoneNumber(), unishop.getAddress(), unishop.getNotes(), unishop.getListOrder(), "UNISHOP"));
                    }
                    if (context.contains("Заказ компьютера по параметрам")){
                        PCForPrametrsParser pcForPrametrsParser = new PCForPrametrsParser();
                        Parser parser = pcForPrametrsParser.parser(linesEmail);
                        parser.setSource("Компьютер по параметрам");
                        orderList.add(parser);
                    }
                    if (context.contains("Здравствуйте, Евгений!")){
                        DealByParser dealByParser = new DealByParser();
                        DealBy dealBy = dealByParser.parser(linesEmail);
                        orderList.add(new Parser(dealBy.getName(), dealBy.getPhoneNumber(), dealBy.getAddress(), dealBy.getListOrder(), "DEAL.BY"));
                    }
                    if (context.contains("Отложенный звонок с сайта")) {
                        DeferredCallParser deferredCallParser = new DeferredCallParser();
                        DeferredCall deferredCall = deferredCallParser.parser(linesEmail);
                        orderList.add(new Parser(deferredCall.getPhoneNumber(),"Отложенный звонок с сайта"));
                    }
                    items.setFlag(Flags.Flag.DELETED, true);
                }
            }
            emailFolder.close(true);
            store.close();
        } catch (MessagingException e) {
            orderList.clear();
            orderList.add(new Parser(e.getMessage()));
            e.printStackTrace();
            return orderList;
        }
        return orderList;
    }

    private static String getTextFromMimeMultipart(
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
