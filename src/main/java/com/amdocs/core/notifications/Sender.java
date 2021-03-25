package com.amdocs.core.notifications;

import com.amdocs.core.notifications.bots.SlackBot;
import com.amdocs.core.notifications.bots.TelegramBot;

public class Sender {
    public static void main(String[] args) {
        System.out.println(System.getProperty("jenkins.build.number"));
        System.out.println(System.getProperty("jenkins.job.url"));
        sendTo(args[0]);
    }

    private static void sendTo(String recipient) {
        switch (recipient) {
            case "telegram":
                new TelegramBot().send();
                break;
            case "slack":
                new SlackBot().send();
                break;
            default:
                throw new RuntimeException("Unsupported recipient " + recipient);
        }
    }
}
