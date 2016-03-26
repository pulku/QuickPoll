package com.pulku.controller;

import com.pulku.repository.PollRepository;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by pınar on 26.03.2016.
 */
@RestController
public class PollController {

    @Inject
    private PollRepository pollRepository;

}
