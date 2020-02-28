package com.birthdayupdateteam.birthdayupdateteambot.transformer;

public interface Transformer<FROM, TO> {
    TO transform(FROM chat);
}
