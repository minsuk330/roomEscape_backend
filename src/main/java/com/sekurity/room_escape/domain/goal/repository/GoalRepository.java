package com.sekurity.room_escape.domain.goal.repository;

import com.sekurity.room_escape.domain.goal.entity.Goal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {

  List<Goal> findAllByOrderByActualTimeAsc();
}
