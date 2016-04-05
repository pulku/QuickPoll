package com.pulku.client;

import com.pulku.domain.Poll;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pınar on 5.04.2016.
 */
public class QuickPollClient {

    private static final String QUICK_POLL_URI_V1 = "http://localhost:8080/v1/polls";
    private RestTemplate restTemplate = new RestTemplate();

    public Poll getPollById(Long pollId) {
        return restTemplate.getForObject(QUICK_POLL_URI_V1 + "/" + pollId, Poll.class, pollId);
    }

    public static void main(String[] args) {
        QuickPollClient client = new QuickPollClient();
        Poll poll = client.getPollById(1L);
        System.out.println(poll);
    }
}
