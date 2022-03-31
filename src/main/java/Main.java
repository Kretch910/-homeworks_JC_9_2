import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=AUg3GUkz8Jih723WpqMr7LeJBwH9smMaRNvGYjmS");

        CloseableHttpResponse responseJson = httpClient.execute(request);

        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(responseJson.getEntity().getContent(), Message.class);

        CloseableHttpResponse responseImage = httpClient.execute(new HttpGet(message.getUrl()));

        String[] parts = message.getUrl().split("/");

        byte[] body = responseImage.getEntity().getContent().readAllBytes();
        try (FileOutputStream fos = new FileOutputStream(parts[parts.length - 1])) {
            fos.write(body, 0, body.length);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
