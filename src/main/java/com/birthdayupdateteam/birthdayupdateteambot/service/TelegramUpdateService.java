package com.birthdayupdateteam.birthdayupdateteambot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import com.birthdayupdateteam.birthdayupdateteambot.model.telegram.TelegramChat;
import com.birthdayupdateteam.birthdayupdateteambot.model.telegram.TelegramMessage;
import com.birthdayupdateteam.birthdayupdateteambot.model.telegram.TelegramUpdate;
import com.birthdayupdateteam.birthdayupdateteambot.model.telegram.TelegramUser;
import com.birthdayupdateteam.birthdayupdateteambot.repository.telegram.TelegramChatRepository;
import com.birthdayupdateteam.birthdayupdateteambot.repository.telegram.TelegramMessageRepository;
import com.birthdayupdateteam.birthdayupdateteambot.repository.telegram.TelegramUpdateRepository;
import com.birthdayupdateteam.birthdayupdateteambot.repository.telegram.TelegramUserRepository;
import com.birthdayupdateteam.birthdayupdateteambot.transformer.Transformer;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TelegramUpdateService {
    Transformer<Update, TelegramUpdate>   updateToTelegramUpdateTransformer;
    Transformer<Message, TelegramMessage> messageToTelegramMessageTransformer;
    Transformer<User, TelegramUser>       userToTelegramUserTransformer;
    Transformer<Chat, TelegramChat>       chatToTelegramChatTransformer;
    TelegramUpdateRepository              telegramUpdateRepository;
    TelegramMessageRepository             telegramMessageRepository;
    TelegramUserRepository                telegramUserRepository;
    TelegramChatRepository                telegramChatRepository;

    public TelegramUpdate save(Update update) {
        TelegramUser telegramUser = telegramUserRepository.findById(update.getMessage().getFrom().getId())
                .orElseGet(() ->
                        telegramUserRepository.save(
                                userToTelegramUserTransformer.transform(update.getMessage().getFrom())
                        )
                );

        TelegramChat telegramChat = telegramChatRepository.findById(update.getMessage().getChat().getId())
                .orElseGet(() ->
                        {
                            TelegramChat transformedChat = chatToTelegramChatTransformer.transform(update.getMessage().getChat());
                            transformedChat.setUser(telegramUser);
                            return telegramChatRepository.save(transformedChat);
                        }
                );

        TelegramMessage telegramMessage = messageToTelegramMessageTransformer.transform(update.getMessage());
        telegramMessage.setFrom(telegramUser);
        telegramMessage.setChat(telegramChat);
        TelegramMessage savedTelegramMessage = telegramMessageRepository.save(telegramMessage);

        TelegramUpdate telegramUpdate = updateToTelegramUpdateTransformer.transform(update);
        telegramUpdate.setMessage(savedTelegramMessage);
        return telegramUpdateRepository.save(telegramUpdate);
    }
}
