package core.notifications;

import core.notifications.bots.slack.SlackBot;
import core.notifications.bots.telegram.TelegramWrapper;

public class Sender {
    public static void main(String[] args) {
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
                throw new RuntimeException("Unsupported recipient type: " + recipient);
        }
    }
}
