package com.ramich.Mimimimetr.services;

import com.ramich.Mimimimetr.entities.Vote;
import com.ramich.Mimimimetr.repos.VoteRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl implements VoteService{

    private VoteRepo voteRepo;

    @Autowired
    public void setVoteRepo(VoteRepo voteRepo) {
        this.voteRepo = voteRepo;
    }

    @Override
    public Vote findVoteByUsernameAndCatId(String username, int catId) {
        return voteRepo.findByUsernameAndCatId(username, catId);
    
    }

    @Override
    public void addVote(Vote vote) {
        voteRepo.save(vote);
    }
    
}
