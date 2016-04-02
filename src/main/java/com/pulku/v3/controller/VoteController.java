package com.pulku.v3.controller;

import com.pulku.domain.Vote;
import com.pulku.repository.VoteRepository;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;

/**
 * Created by pınar on 27.03.2016.
 */
@RestController("voteControllerV3")
@RequestMapping("/v3/")
public class VoteController {

    @Inject
    private VoteRepository voteRepository;

    @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.POST)
    @ApiOperation(value = "Casts a new vote for a given poll",
            notes = "The newly created vote Id will be sent in the location response header.", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Vote created successfully", response = Void.class)})
    public ResponseEntity<Void> createVote(@PathVariable Long pollId, @RequestBody Vote vote) {
        vote = voteRepository.save(vote);


        //Set the headers for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest()
                                                               .path("/{id}")
                                                               .buildAndExpand(vote.getId())
                                                               .toUri());

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieves all the votes", response = Vote.class, responseContainer = "List")
    public Iterable<Vote> getAllVotes(@PathVariable Long pollId, Pageable pageable) {
        return voteRepository.findByPoll(pollId);
    }

}
