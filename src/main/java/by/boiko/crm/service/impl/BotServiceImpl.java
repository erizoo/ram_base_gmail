package by.boiko.crm.service.impl;

import by.boiko.crm.service.BotService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@Service
@Transactional
public class BotServiceImpl extends TelegramLongPollingBot implements BotService  {

    private String mes = null;

    @Override
    public void getMessage(String message) {
        ApiContextInitializer.init(); // Инициализируем апи
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(new Bot());
            mes = message;
            sendMessage(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    private void sendMessage(String message) {
        SendMessage s = new SendMessage();// Боту может писать не один человек, и поэтому чтобы отправить сообщение, грубо говоря нужно узнать куда его отправлять
        s.setText(message);
            try { //Чтобы не крашнулась программа при вылете Exception
                sendMessage(s);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update e) {
        Message msg = e.getMessage(); // Это нам понадобится
        String txt = msg.getText();
        switch (txt){
            case "/newmanager valery":
                sendMsg(msg, "Добрый день, Валерий! В этом чате Вы будете получать сообщения с сайта! " +
                        "Приятной работы!");
            case "/newmanager sergei":
                sendMsg(msg, "Добрый день, Сергей! В этом чате Вы будете получать сообщения с сайта! " +
                        "Приятной работы!");
            default:
                sendMsg(msg, "Добрый день, такого менеджера не сущесвтует!");
                break;
        }
    }

    @Override
    public String getBotUsername() {
        return "ramBotBot";
    }

    @Override
    public String getBotToken() {
        return "567680454:AAHnKAMpT4su5BNv1iCYJLbctKNYPQ5Cin4";
    }

    @SuppressWarnings("deprecation") // Означает то, что в новых версиях метод уберут или заменят
    private void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId()); // Боту может писать не один человек, и поэтому чтобы отправить сообщение, грубо говоря нужно узнать куда его отправлять
        s.setText(text);
        try { //Чтобы не крашнулась программа при вылете Exception
            sendMessage(s);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
