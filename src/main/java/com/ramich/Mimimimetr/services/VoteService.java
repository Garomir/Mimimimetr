package com.ramich.Mimimimetr.services;

import com.ramich.Mimimimetr.entities.Vote;

public interface VoteService {
    Vote findVoteByUsernameAndCatId(String username, int catId);
    void addVote(Vote vote);
}
