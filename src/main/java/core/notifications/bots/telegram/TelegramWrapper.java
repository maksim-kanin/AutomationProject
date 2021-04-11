package core.notifications.bots.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import core.notifications.bots.MessageProvider;
import core.notifications.pojo.AllureSummary;
import core.notifications.pojo.Statistic;
import core.utils.JenkinsUtils;

public class TelegramWrapper implements MessageProvider {
    private static final String SECRET_BOT_TOKEN = "1796571092:AAHGPypoDtHNgjjsfriIK6iBzjlqSqCMAQ8";
    //ID for "QA.GURU 4| Группа 9"
    private static final String CHAT_ID = "-1001447250696";
    private final TelegramBot bot = new TelegramBot(SECRET_BOT_TOKEN);

    @Override
    public void send() {
        SendMessage message = new SendMessage(CHAT_ID, beautifySummary(JenkinsUtils.getSummary()))
                .parseMode(ParseMode.HTML);
        bot.execute(message);
    }

    private String beautifySummary(AllureSummary summary) {
        Statistic statistic = summary.getStatistic();
        StringBuilder builder = new StringBuilder();
        builder.append("<b>Build number: </b>")
                .append(System.getProperty("jenkins.build.number"))
                .append("\r\n")
                .append("<b>Build URL: </b>")
                .append(System.getProperty("jenkins.build.url"))
                .append("\r\n")
                .append("<b>Total: </b>")
                .append(statistic.getTotal())
                .append("\r\n")
                .append("<b>Passed: </b>")
                .append(statistic.getPassed())
                .append("\r\n")
                .append("<b>Failed: </b>")
                .append(statistic.getFailed())
                .append("\r\n")
                .append("<b>Broken: </b>")
                .append(statistic.getBroken())
                .append("\r\n")
                .append("<b>Skipped: </b>")
                .append(statistic.getSkipped());
        return builder.toString();
    }
}
