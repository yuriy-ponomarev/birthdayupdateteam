package com.birthdayupdateteam.birthdayupdateteambot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.birthdayupdateteam.birthdayupdateteambot.hendler.TelegramMessageHandler;
import com.birthdayupdateteam.birthdayupdateteambot.model.telegram.TelegramUpdate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bot extends TelegramLongPollingBot {
    public static final String HELLO_BUTTON = "Hello";
    public static final String START_COMMAND = "/start";
    public static final String HELP_BUTTON = "Help";

    @Getter
    @Value("${botUsername}")
    String botUsername;
    @Getter
    @Value("${botToken}")
    String botToken;

    final TelegramUpdateService        telegramUpdateService;
    final List<TelegramMessageHandler> telegramMessageHandlers;

    @Autowired
    public Bot( TelegramUpdateService telegramUpdateService,
                @Lazy List<TelegramMessageHandler> telegramMessageHandlers) {
        this.telegramUpdateService = telegramUpdateService;
        this.telegramMessageHandlers = telegramMessageHandlers;
    }


    @Override
    public void onUpdateReceived(Update update) {
        TelegramUpdate telegramUpdate = telegramUpdateService.save(update);
        telegramMessageHandlers.forEach(
                telegramMessageHandler -> telegramMessageHandler.handle(telegramUpdate)
        );
    }

    public synchronized void sendTextMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);

        sendMessage.setReplyMarkup(getCustomReplyKeyboardMarkup());

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e);
        }
    }

    private ReplyKeyboardMarkup getCustomReplyKeyboardMarkup() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton(HELLO_BUTTON));

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton(HELP_BUTTON));

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

}