package com.example.StrangeTravelBot.service;

import com.example.StrangeTravelBot.entity.MainSender;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public final class SenderService {

    private final Set<MainSender> senderSet;

    public SenderService() {
        senderSet = new HashSet<>();
    }
    public boolean removeSender(User user) {
        return senderSet.removeIf(a -> a.getUser().equals(user));
    }

    public boolean addSender(MainSender sender) {
        return senderSet.add(sender);
    }

    public boolean hasSender(User user) {
        return senderSet.stream().anyMatch(a -> a.getUser().equals(user));
    }

    public boolean setUserDisplayedName(User user, String name) {
        if (!isDisplayedNameTaken(name)) {
            senderSet.stream().filter(a -> a.getUser().equals(user)).forEach(a -> a.setDisplayedName(name));
            return true;
        }
        return false;
    }

    public String getDisplayedName(User user) {
        MainSender mainSender = senderSet.stream().filter(a -> a.getUser().equals(user)).findFirst().orElse(null);
        if (mainSender == null) {
            return null;
        }
        return mainSender.getDisplayedName();
    }

    public Stream<MainSender> senders() {
        return senderSet.stream();
    }

    private boolean isDisplayedNameTaken(String name) {
        return senderSet.stream().anyMatch(a -> Objects.equals(a.getDisplayedName(), name));
    }

}
