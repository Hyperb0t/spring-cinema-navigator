package ru.itis.springcinemanavigator.services.externalapi;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class FilmRecommendService {

    public List<Map<String, String>> getRecommendations(String queryTitle) {
        List<Map<String, String>> result = new LinkedList<>();
        String url = "http://www.omdbapi.com/";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("apikey", "5ccfb63d");
        parameters.put("s", queryTitle);
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.get(url)
                    .queryString(parameters)
                    .asJson();
        } catch (UnirestException e) {
            throw new IllegalStateException("something wrong with request to api");
        }
        if(response.getBody().getObject().getBoolean("Response")) {
            response.getBody().getObject().getJSONArray("Search").iterator()
                    .forEachRemaining(o -> {
                        log.info(((JSONObject) o).get("Title").toString());
                        Map<String, String> map = new HashMap<>();
                        map.put("Title", ((JSONObject) o).getString("Title"));
                        map.put("Year", ((JSONObject) o).getString("Year"));
                        map.put("Poster", ((JSONObject) o).getString("Poster"));
                        result.add(map);
                    });
        }
        else {
            log.info("nothing was found with api: " + response);
        }
        return result;
    }
}

