package com.ramich.Mimimimetr.repos;

import com.ramich.Mimimimetr.entities.Vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepo extends JpaRepository<Vote, Integer> {
    /*@Query(value = "select sum(likes) from votes where cat_id = :catId",nativeQuery = true)
    int findSumLikesByCatId(@Param("catId")int catId);*/
    Vote findByUsernameAndCatId(String username, int catId);
}