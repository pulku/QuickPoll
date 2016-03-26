package com.pulku.repository;

import com.pulku.domain.Vote;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by pınar on 26.03.2016.
 */
public interface VoteRepository extends CrudRepository<Vote, Long> {
}
