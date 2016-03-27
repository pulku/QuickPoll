package com.pulku.repository;

import com.pulku.domain.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by pınar on 26.03.2016.
 */
public interface VoteRepository extends CrudRepository<Vote, Long> {

    @Query(value = "select v.* from Option o, Vote v where o.POLL_ID = ?1 and v.OPTION_ID = o.OPTION_ID", nativeQuery = true)
    public Iterable<Vote> findByPoll(Long pollId);
}
