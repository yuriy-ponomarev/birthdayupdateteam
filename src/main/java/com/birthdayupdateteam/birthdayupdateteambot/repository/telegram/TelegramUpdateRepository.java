package com.birthdayupdateteam.birthdayupdateteambot.repository.telegram;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.birthdayupdateteam.birthdayupdateteambot.model.telegram.TelegramUpdate;

@RepositoryRestResource(collectionResourceRel = "telegram_updates", path = "updates")
public interface TelegramUpdateRepository extends PagingAndSortingRepository<TelegramUpdate, Integer> {
}
