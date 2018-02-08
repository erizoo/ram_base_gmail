package by.boiko.crm.service.impl;

import by.boiko.crm.model.Parser;
import by.boiko.crm.service.impl.ParserMail.Configurator;
import by.boiko.crm.service.impl.ParserMail.ConfiguratorParser;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class YandexMail {

    private static final String HOST = "pop.yandex.ru";
    private static final String USERNAME = "ram.misnk@yandex.by";
    private static final String PASSWORD = "smartshop12";
    private static final String MAIL_STORE_TYPE = "pop3";
    private static final String MAIL_PORT = "995";
    private static final String TYPE_EMAIL = "INBOX";
    private static final String TYPE_STORE = "pop3s";
    private static List<Parser> orderList = new ArrayList<>();

    public static void check(String host, String storeType, String user,
                             String password) throws IOException {
        try {
            Message message;
            Properties properties = new Properties();
            properties.put("mail.pop3.host", HOST);
            properties.put("mail.pop3.port", MAIL_PORT);
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore(TYPE_STORE);
            store.connect(host, user, password);
            Folder emailFolder = store.getFolder(TYPE_EMAIL);
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages = emailFolder.getMessages();
            if (messages.length == 0) {
                System.out.println("No emails");
            } else {
                System.out.println("messages.length---" + messages.length);
                Object content = null;
                for (Message items : messages) {
                    System.out.println("Subject: " + items.getSubject());
                    System.out.println("From: " + items.getFrom()[0]);
                    content = items.getContent();
                    String context = null;
                    if (content instanceof String) {
                        context = (String) content;
                    } else if (content instanceof Multipart) {
                        Multipart mp = (Multipart) content;
                        context = getTextFromMimeMultipart((MimeMultipart) mp);
                        System.out.println(context);
                    }
                    assert context != null;
                    String[] linesEmail = context.split("[\\r\\n]+", -1);
                    if (items.getFrom()[0].toString().contains("Configurator")){
                        ConfiguratorParser configuratorParser = new ConfiguratorParser();
                        Configurator configurator = configuratorParser.parser(linesEmail);
                        orderList.add(new Parser(configurator.getName(), configurator.getPhoneNumber(), configurator.getAddress(), configurator.getNotes(), configurator.getListOrder(), "Конфигуратор"));
                    }
                        store.close();
                }
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        check(HOST, MAIL_STORE_TYPE, USERNAME, PASSWORD);

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
