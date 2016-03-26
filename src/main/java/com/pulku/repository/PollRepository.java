package com.pulku.repository;

import com.pulku.domain.Poll;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by pınar on 26.03.2016.
 */
public interface PollRepository extends CrudRepository<Poll, Long> {
}
