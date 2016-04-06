package com.pulku.client;

import com.pulku.domain.Poll;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Created by pınar on 6.04.2016.
 */
public class QuickPollClientV2 {

    private static final String QUICK_POLL_URI_2 = "http://localhost:8080/v2/polls";
    private RestTemplate restTemplate;

    public PageWrapper<Poll> getAllPolls(int page, int size) {
        ParameterizedTypeReference<PageWrapper<Poll>> responseType = new ParameterizedTypeReference<PageWrapper<Poll>>() {};
        UriComponentsBuilder builder = UriComponentsBuilder
                                                .fromHttpUrl(QUICK_POLL_URI_2)
                                                .queryParam("page", page)
                                                .queryParam("size", size);
        ResponseEntity<PageWrapper<Poll>> responseEntity = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, null, responseType);
        return responseEntity.getBody();
    }

    public Poll getPollById(Long pollId) {
        return restTemplate.getForObject(QUICK_POLL_URI_2 + "/{pollId}", Poll.class, pollId);
    }

    public URI createPoll(Poll poll) {
        return restTemplate.postForLocation(QUICK_POLL_URI_2, poll);
    }

    public void updatePoll(Poll poll) {
        restTemplate.put(QUICK_POLL_URI_2 + "/{pollId}", poll, poll.getId());
    }

    public void deletePoll(Long pollId) {
        restTemplate.delete(QUICK_POLL_URI_2 + "/{pollId}", pollId);
    }
}
