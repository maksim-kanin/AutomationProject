package com.amdocs.core.notifications;

import com.amdocs.core.notifications.bots.slack.SlackBot;
import com.amdocs.core.notifications.bots.telegram.TelegramWrapper;

public class Sender {
    public static void main(String[] args) {
        System.out.println(System.getProperty("jenkins.build.number"));
        System.out.println(System.getProperty("jenkins.build.url"));
        System.out.println(System.getProperty("jenkins.job.url"));
        sendTo(args[0]);
    }

    private static void sendTo(String recipient) {
        switch (recipient) {
            case "telegram":
                new TelegramWrapper().send();
                break;
            case "slack":
                new SlackBot().send();
                break;
            default:
                throw new RuntimeException("Unsupported recipient " + recipient);
        }
    }
}
