package com.birthdayupdateteam.birthdayupdateteambot.hendler;

import com.birthdayupdateteam.birthdayupdateteambot.model.telegram.TelegramUpdate;

public interface TelegramMessageHandler {
    void handle( TelegramUpdate telegramUpdate);
}
