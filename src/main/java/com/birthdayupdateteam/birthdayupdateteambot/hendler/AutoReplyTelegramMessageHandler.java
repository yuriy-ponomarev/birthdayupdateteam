package com.birthdayupdateteam.birthdayupdateteambot.hendler;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class AutoReplyTelegramMessageHandler implements TelegramMessageHandler {
    Bot avp256Bot;

    @Override
    public void handle( TelegramUpdate telegramUpdate) {
        if (telegramUpdate.getMessage().getText().startsWith(Bot.START_COMMAND)
                || telegramUpdate.getMessage().getText().startsWith(Bot.HELP_BUTTON)
                || telegramUpdate.getMessage().getText().startsWith( Bot.HELLO_BUTTON)) {
            return;
        }
        Long chatId = telegramUpdate.getMessage().getChat().getId();
        TelegramUser user = telegramUpdate.getMessage().getFrom();
        String text = Stream.of(user.getLastName(), user.getFirstName(), "said:", telegramUpdate.getMessage().getText())
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "));
        avp256Bot.sendTextMessage(chatId, text);
    }
}
