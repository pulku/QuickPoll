package com.pulku.client;

import com.pulku.domain.Poll;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by pınar on 5.04.2016.
 */
public class QuickPollClient {

    private static final String QUICK_POLL_URI_V1 = "http://localhost:8080/v1/polls";
    private RestTemplate restTemplate = new RestTemplate();

    public Poll getPollById(Long pollId) {
        return restTemplate.getForObject(QUICK_POLL_URI_V1 + "/" + pollId, Poll.class, pollId);
    }

    public List<Poll> getAllPolls() {
        ParameterizedTypeReference<List<Poll>> responseType = new ParameterizedTypeReference<List<Poll>>() {};
        ResponseEntity<List<Poll>> responseEntity = restTemplate.exchange(QUICK_POLL_URI_V1, HttpMethod.GET, null, responseType);
        List<Poll> allPolls = responseEntity.getBody();
        return allPolls;
    }

    public static void main(String[] args) {
        QuickPollClient client = new QuickPollClient();
        List<Poll> polls = client.getAllPolls();
        System.out.println(polls);
    }
}
