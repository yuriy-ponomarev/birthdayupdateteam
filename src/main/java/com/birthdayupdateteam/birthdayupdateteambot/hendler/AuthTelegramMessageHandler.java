package com.birthdayupdateteam.birthdayupdateteambot.hendler;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.birthdayupdateteam.birthdayupdateteambot.model.telegram.TelegramUpdate;
import com.birthdayupdateteam.birthdayupdateteambot.model.telegram.TelegramUser;
import com.birthdayupdateteam.birthdayupdateteambot.repository.PersonRepository;
import com.birthdayupdateteam.birthdayupdateteambot.repository.telegram.TelegramUserRepository;
import com.birthdayupdateteam.birthdayupdateteambot.service.Bot;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthTelegramMessageHandler implements TelegramMessageHandler {
    Bot                    bot;
    PersonRepository       personRepository;
    TelegramUserRepository telegramUserRepository;

    @Override
    public void handle( TelegramUpdate telegramUpdate) {
        if (!telegramUpdate.getMessage().getText().startsWith( Bot.START_COMMAND)
                || Objects.nonNull(telegramUpdate.getMessage().getFrom().getPerson())) {
            return;
        }
        String authCode = telegramUpdate.getMessage().getText().replace(Bot.START_COMMAND, "").trim();
        personRepository.findByAuthCode(authCode)
                .ifPresent(person -> {
                    TelegramUser user = telegramUpdate.getMessage().getFrom();
                    user.setPerson(person);
                    telegramUserRepository.save(user);

                    Long chatId = telegramUpdate.getMessage().getChat().getId();
                    String text = "You have been authorized as " + person.getName();
                    bot.sendTextMessage(chatId, text);
                });
    }
}
