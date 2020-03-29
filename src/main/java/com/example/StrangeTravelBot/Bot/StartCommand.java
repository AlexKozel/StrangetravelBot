package com.example.StrangeTravelBot.Bot;

import com.example.StrangeTravelBot.entity.MainSender;
import com.example.StrangeTravelBot.service.SenderService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public final class StartCommand extends SenderCommands {

    private final SenderService senderService;

    public StartCommand(SenderService service) {
        super("start", "start using bot\n");
        senderService = service;
    }


    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        log.debug("execute {} {}", user.getId(), getCommandIdentifier());

        StringBuilder sb = new StringBuilder();

        SendMessage message = new SendMessage();
        message.setChatId(chat.getId().toString());

        if (senderService.addSender(new MainSender(user, chat))) {
            log.info("User {} is trying to execute '{}' the first time. Added to users' list.", user.getId(), getCommandIdentifier());
            sb.append("Hi, ").append(user.getUserName()).append("! Input any Capital!\n");
        } else {
            log.info("User {} has already executed '{}' ", user.getId(), getCommandIdentifier());
            sb.append("You've already started bot! Please, input any Capital!");
        }

        message.setText(sb.toString());
        execute(absSender, message, user);
    }
}
