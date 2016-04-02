package com.pulku.v1.controller;

import com.pulku.domain.Poll;
import com.pulku.dto.error.ErrorDetail;
import com.pulku.exception.ResourceNotFoundException;
import com.pulku.repository.PollRepository;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;

/**
 * Created by pınar on 26.03.2016.
 */
@RestController("pollControllerV1")
@RequestMapping("/v1/")
@Api(value = "polls", description = "Poll API")
public class PollController {

    @Inject
    private PollRepository pollRepository;

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Poll poll = pollRepository.findOne(pollId);
        if(poll == null) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }
    }

    @RequestMapping(value = "/polls", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieves all the polls", response = Poll.class, responseContainer = "List")
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity<Iterable<Poll>>(pollRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/polls", method = RequestMethod.POST)
    @ApiOperation(value = "Creates a new Poll", notes = "The newly created poll Id will be sent in the location response header", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Poll created successfully", response = Void.class),
            @ApiResponse(code = 500, message = "Error creating poll", response = ErrorDetail.class)})
    public ResponseEntity<Void> createPoll(@Valid @RequestBody Poll poll) {
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
    @ApiOperation(value = "Retrieves the Poll associated with the poll Id", response = Poll.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = Void.class),
            @ApiResponse(code = 404, message = "Unable to find poll", response = ErrorDetail.class)})
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        Poll poll =pollRepository.findOne(pollId);
        return new ResponseEntity<Object>(poll, HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.PUT)
    @ApiOperation(value = "Updates given poll", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = Void.class),
            @ApiResponse(code = 404, message = "Unable to find poll", response = ErrorDetail.class)})
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId){
        verifyPoll(pollId);
        //Save the entity
        pollRepository.save(poll);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Deletes given poll", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "", response = Void.class),
            @ApiResponse(code = 404, message = "Unable to find poll", response = ErrorDetail.class)})
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId){
        verifyPoll(pollId);
        pollRepository.delete(pollId);
        return  new ResponseEntity<Object>(HttpStatus.OK);
    }
}
