package com.birthdayupdateteam.birthdayupdateteambot.transformer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.birthdayupdateteam.birthdayupdateteambot.model.telegram.TelegramUpdate;

@Component
public class UpdateToTelegramUpdateTransformer implements Transformer<Update, TelegramUpdate> {
    @Override
    public TelegramUpdate transform(Update update) {
        return TelegramUpdate.builder()
                .id(update.getUpdateId())
                .creationDate(LocalDateTime.now())
                .build();
    }
}
