package by.boiko.crm.service.impl;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init(); // Инициализируем апи
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "ramBotBot";
        //возвращаем юзера
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
    public String getBotToken() {
        return "567680454:AAHnKAMpT4su5BNv1iCYJLbctKNYPQ5Cin4";
        //Токен бота
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