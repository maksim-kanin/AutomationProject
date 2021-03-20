package com.amdocs.core.notifications.bots;

import com.amdocs.core.notifications.pojo.AllureSummary;
import com.amdocs.core.notifications.pojo.Statistic;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

import static com.amdocs.core.utils.JenkinsUtils.getSummary;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static org.assertj.core.api.Assertions.assertThat;

public class TelegramBot implements MessageProvider {
    private static final String SECRET_BOT_TOKEN = "1796571092:AAHGPypoDtHNgjjsfriIK6iBzjlqSqCMAQ8";
    //ID for "QA.GURU 4| Группа 9"
    private static final String CHAT_ID = "-1001447250696";

    @Override
    public void send() {
        HttpResponse response = null;
        try {
            response = Request.Post("https://api.telegram.org/bot" + SECRET_BOT_TOKEN + "/sendMessage")
                    .bodyString(new Body(CHAT_ID, beautifySummary(getSummary())).bodyAsString(), APPLICATION_JSON)
                    .execute()
                    .returnResponse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int statusCode = response.getStatusLine().getStatusCode();
        assertThat(statusCode == 200)
                .as("Status code must be 200 OK, but it was " + statusCode)
                .isTrue();
    }

    private String beautifySummary(AllureSummary summary) {
        Statistic statistic = summary.getStatistic();
        StringBuilder builder = new StringBuilder();
        builder.append("TOTAL: ")
                .append(statistic.getTotal())
                .append("\r\n")
                .append("PASSED: ")
                .append(statistic.getPassed())
                .append("\r\n")
                .append("FAILED: ")
                .append(statistic.getFailed())
                .append("\r\n")
                .append("BROKEN: ")
                .append(statistic.getBroken())
                .append("\r\n")
                .append("SKIPPED: ")
                .append(statistic.getSkipped());
        return builder.toString();
    }

    private class Body {
        private final String chatId;
        private final String text;

        public Body(String chatId, String message) {
            this.chatId = chatId;
            this.text = message;
        }

        public String bodyAsString() {
            return "{" +
                    "\"chat_id\":\"" + chatId + "\", " +
                    "\"text\":\"" + text + "\"}";
        }
    }
}
