package com.birthdayupdateteam.birthdayupdateteambot.repository.telegram;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.birthdayupdateteam.birthdayupdateteambot.model.telegram.TelegramMessage;

@RepositoryRestResource(collectionResourceRel = "telegram_messages", path = "messages")
public interface TelegramMessageRepository extends PagingAndSortingRepository<TelegramMessage, Integer> {
}
