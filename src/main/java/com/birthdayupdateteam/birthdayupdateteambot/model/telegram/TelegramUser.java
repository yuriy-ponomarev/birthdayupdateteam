package com.birthdayupdateteam.birthdayupdateteambot.model.telegram;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.birthdayupdateteam.birthdayupdateteambot.model.Person;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@EqualsAndHashCode(of = {"id"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramUser {
    @Id
    Integer id;
    LocalDateTime creationDate;
    String userName;
    Boolean bot;
    String lastName;
    String firstName;
    String languageCode;

    @ManyToOne
    Person person;
}
