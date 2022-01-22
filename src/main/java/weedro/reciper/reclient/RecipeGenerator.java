package weedro.reciper.reclient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class RecipeGenerator {

    private RecipeGenerator() {
    }

    private static String RECIPE_GENERATOR_URL = "http://localhost:8080/generate";
    private static String REQUEST_FORMAT = "{\"username\":\"%s\"}";

    public static String generate(String username)
        throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = buildRequest(username);
        HttpResponse<String> response = HttpClient.newBuilder().build()
            .send(request, BodyHandlers.ofString());
        return response.body();
    }

    private static HttpRequest buildRequest(String username) throws URISyntaxException {
        return HttpRequest.newBuilder()
            .uri(new URI(RECIPE_GENERATOR_URL))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(REQUEST_FORMAT.formatted(username)))
            .build();
    }
}
