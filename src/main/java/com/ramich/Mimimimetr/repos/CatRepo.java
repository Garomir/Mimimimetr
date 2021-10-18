package com.ramich.Mimimimetr.repos;

import com.ramich.Mimimimetr.entities.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepo extends JpaRepository<Cat, Integer> {
    List<Cat> findTop10ByOrderByLikesDesc();
}