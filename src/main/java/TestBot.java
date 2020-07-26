
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import java.net.*;
import java.io.DataInputStream;

public class TestBot extends TelegramLongPollingBot {

    public static void main(String[] args) throws Exception {
        ApiContextInitializer.init();
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(new TestBot());
        } catch (TelegramApiException exc) {
            exc.printStackTrace();
            System.out.println("ERRORS");
        }
        System.out.println("Bot work started");
    }

    private static String getProperty(String properyName) throws Exception {
        String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
        FileInputStream fileInputStream;
        Properties prop = new Properties();
        fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
        prop.load(fileInputStream);
        String result = prop.getProperty(properyName);
        return result;

    }

    @Override
    public String getBotToken() {
        String botToken = new String();
        try {
            botToken = getProperty("botToken");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return botToken;
    }

    @Override
    public String getBotUsername() {
        String botName = new String();
        try {
            botName = getProperty("botName");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return botName;
    }

    private void answerCallbackQuery(String message, Long chatId) {
        SendMessage answer = new SendMessage();
        answer.setText(message);
        answer.setChatId(chatId);
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update e) {
        Message msg = e.getMessage();
        Long chatId = msg.getChatId();
        String txt = msg.getText();
        System.out.println(msg);
        if (txt.equals("/start")) {
            answerCallbackQuery("Hello, world! This is simple bot!", chatId);
        }
    }
}
