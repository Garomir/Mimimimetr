package com.ramich.Mimimimetr.services;

import com.ramich.Mimimimetr.entities.Vote;

import java.util.List;

public interface VoteService {
    Vote findVoteByUsernameAndCatId(String username, int catId);
    void addVote(Vote vote);
    void deleteAllVotes();
}
