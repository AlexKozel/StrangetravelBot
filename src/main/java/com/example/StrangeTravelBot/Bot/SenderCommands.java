package com.example.StrangeTravelBot.Bot;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

abstract class SenderCommands extends BotCommand {

    final Logger log = LogManager.getLogger(getClass());

    SenderCommands(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    void execute(AbsSender sender, SendMessage message, User user) {
        try {
            sender.execute(message);
            log.debug("execute message{}", message);
        } catch (TelegramApiException e) {
            log.error("Error with user {},  {}, error - {}", user.getId(), getCommandIdentifier(), e);
        }
    }
}
