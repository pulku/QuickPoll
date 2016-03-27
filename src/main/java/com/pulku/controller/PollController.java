package com.pulku.controller;

import com.pulku.domain.Poll;
import com.pulku.repository.PollRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import java.net.URI;

/**
 * Created by pınar on 26.03.2016.
 */
@RestController
public class PollController {

    @Inject
    private PollRepository pollRepository;

    @RequestMapping(value = "/polls", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity<Iterable<Poll>>(pollRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/polls", method = RequestMethod.POST)
    public ResponseEntity<?> createPoll(@RequestBody Poll poll) {
        poll = pollRepository.save(poll);

        //Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollURI = ServletUriComponentsBuilder
                                        .fromCurrentRequest()
                                        .path("{/id}")
                                        .buildAndExpand(poll.getId())
                                        .toUri();
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        Poll poll =pollRepository.findOne(pollId);
        return new ResponseEntity<Object>(poll, HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId){
        //Save the entity
        Poll p = pollRepository.save(poll);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId){
        pollRepository.delete(pollId);
        return  new ResponseEntity<Object>(HttpStatus.OK);
    }
}
