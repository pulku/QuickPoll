package com.pulku.dto;

import java.util.Collection;

/**
 * Created by pınar on 28.03.2016.
 */
public class VoteResult {

    private int totalvotes;
    private Collection<OptionCount> results;

    public int getTotalvotes() {
        return totalvotes;
    }

    public void setTotalvotes(int totalvotes) {
        this.totalvotes = totalvotes;
    }

    public Collection<OptionCount> getResults() {
        return results;
    }

    public void setResults(Collection<OptionCount> results) {
        this.results = results;
    }
}
