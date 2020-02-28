package com.birthdayupdateteam.birthdayupdateteambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class BirthdayUpdateTeamBot {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run( BirthdayUpdateTeamBot.class, args);
	}

}
