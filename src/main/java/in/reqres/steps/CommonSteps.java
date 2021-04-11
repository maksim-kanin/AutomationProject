package in.reqres.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.restassured.response.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class CommonSteps {
    public Response checkResponseWithStatusCode(Response response, int statusCode) {
        response.then()
                .statusCode(statusCode);
        return response;
    }

    public InputStream getUserInputStream(String yaml) {
        return Objects.requireNonNull(Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("rest/user/" + yaml));
    }

    public <T> T yamlToBean(InputStream pathToYAML, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        T bean;
        try {
            bean = mapper.readValue(pathToYAML, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bean;
    }
}
