package com.sekurity.room_escape.domain.goal.repository;

import com.sekurity.room_escape.domain.goal.entity.Goal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GoalRepository extends JpaRepository<Goal, Long> {

  List<Goal> findAllByOrderByActualTimeAsc();

  @Query("select g from Goal g where g.member.teamName = :teamName")
  Goal findByMember(@Param("teamName") String teamName);
}
