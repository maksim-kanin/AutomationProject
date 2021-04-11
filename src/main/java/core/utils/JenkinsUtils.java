package core.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.JenkinsConfig;
import core.notifications.pojo.AllureSummary;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static org.apache.http.auth.AuthScope.ANY;

public class JenkinsUtils {
    private static final String JOB_URL = System.getProperty("jenkins.job.url");
    private static final String BUILD_NUMBER = System.getProperty("jenkins.build.number");

    public static AllureSummary getSummary() {
        try {
            return new ObjectMapper().readValue(summaryAsString(), AllureSummary.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String summaryAsString() {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials
                = new UsernamePasswordCredentials(JenkinsConfig.JENKINS_CONFIG.getJenkinsUserName(), JenkinsConfig.JENKINS_CONFIG.getJenkinsUserPassword());
        provider.setCredentials(ANY, credentials);
        SSLContext sslContext = null;
        try {
            sslContext = new SSLContextBuilder()
                    .loadTrustMaterial(null, (x509CertChain, authType) -> true)
                    .build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            throw new RuntimeException(e);
        }

        HttpClient client = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider)
                .setSSLContext(sslContext)
                .setConnectionManager(
                        new PoolingHttpClientConnectionManager(
                                RegistryBuilder.<ConnectionSocketFactory>create()
                                        .register("http", PlainConnectionSocketFactory.INSTANCE)
                                        .register("https", new SSLConnectionSocketFactory(sslContext,
                                                NoopHostnameVerifier.INSTANCE))
                                        .build()
                        ))
                .build();
        try {
            String encoded = Base64.getEncoder().encodeToString((JenkinsConfig.JENKINS_CONFIG.getJenkinsUserName() + ":" + JenkinsConfig.JENKINS_CONFIG.getJenkinsUserPassword()).getBytes());
            HttpGet request = new HttpGet(JOB_URL + BUILD_NUMBER + "/allure/widgets/summary.json");
            request.addHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoded);
            HttpResponse response = client.execute(request);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
