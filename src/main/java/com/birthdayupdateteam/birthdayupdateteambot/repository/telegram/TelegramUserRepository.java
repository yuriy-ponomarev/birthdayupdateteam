package com.birthdayupdateteam.birthdayupdateteambot.repository.telegram;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.birthdayupdateteam.birthdayupdateteambot.model.telegram.TelegramUser;

@RepositoryRestResource(collectionResourceRel = "telegram_users", path = "users")
public interface TelegramUserRepository extends PagingAndSortingRepository<TelegramUser, Integer> {
}
