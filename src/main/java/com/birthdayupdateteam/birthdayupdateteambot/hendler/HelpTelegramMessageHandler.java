package com.birthdayupdateteam.birthdayupdateteambot.hendler;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.birthdayupdateteam.birthdayupdateteambot.model.telegram.TelegramUpdate;
import com.birthdayupdateteam.birthdayupdateteambot.model.telegram.TelegramUser;
import com.birthdayupdateteam.birthdayupdateteambot.service.Bot;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class HelpTelegramMessageHandler implements TelegramMessageHandler {
    Bot bot;

    @Override
    public void handle( TelegramUpdate telegramUpdate) {
        if (!telegramUpdate.getMessage().getText().startsWith(Bot.HELP_BUTTON)) {
            return;
        }
        Long chatId = telegramUpdate.getMessage().getChat().getId();
        String text;
        if (Objects.isNull(telegramUpdate.getMessage().getFrom().getPerson())) {
            text = "Help service is allowed only for authorized users";
        } else {
            text = "We will help you";
        }
        TelegramUser user = telegramUpdate.getMessage().getFrom();
        bot.sendTextMessage(chatId, text);
    }
}
