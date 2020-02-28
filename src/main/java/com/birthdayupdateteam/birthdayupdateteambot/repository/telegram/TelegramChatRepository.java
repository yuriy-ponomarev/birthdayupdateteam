package com.birthdayupdateteam.birthdayupdateteambot.repository.telegram;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.birthdayupdateteam.birthdayupdateteambot.model.telegram.TelegramChat;

@RepositoryRestResource(collectionResourceRel = "telegram_chats", path = "chats")
public interface TelegramChatRepository extends PagingAndSortingRepository<TelegramChat, Long> {
    Optional<TelegramChat> findByUser_Id(Integer userId);

    Optional<TelegramChat> findByUser_Person_Id(Integer personId);
}
