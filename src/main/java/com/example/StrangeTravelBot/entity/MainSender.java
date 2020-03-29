package com.example.StrangeTravelBot.entity;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

@Data
public final class MainSender {

    private static final Logger LOG = LogManager.getLogger(MainSender.class);
    private static final String USER_CHAT_CANNOT_BE_NULL = "User or chat cannot be null!";

    private final User user;
    private final Chat chat;
    private String DisplayedName;

    public MainSender(User dirUser, Chat dirChat) {
        if (dirUser == null || dirChat == null) {
            LOG.error(USER_CHAT_CANNOT_BE_NULL);
            throw new IllegalStateException(USER_CHAT_CANNOT_BE_NULL);
        }
        user = dirUser;
        chat = dirChat;
    }

    @Override
    public int hashCode() {
        return user.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MainSender && ((MainSender) obj).getUser().equals(user);
    }
}
