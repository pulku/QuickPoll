package com.pulku.repository;

import com.pulku.domain.Poll;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by pınar on 26.03.2016.
 */
public interface PollRepository extends PagingAndSortingRepository<Poll, Long> {
}
