package com.sekurity.room_escape.domain.goal.service;

import com.sekurity.room_escape.domain.goal.entity.Goal;
import com.sekurity.room_escape.domain.goal.entity.dto.req.GoalStartReq;
import com.sekurity.room_escape.domain.goal.entity.dto.resp.GoalResp;
import com.sekurity.room_escape.domain.goal.repository.GoalRepository;
import com.sekurity.room_escape.domain.member.entity.Member;
import java.time.Duration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GoalService {

  private final GoalRepository goalRepository;

  @Transactional
  public Goal save(GoalStartReq req, Member member) {
    return goalRepository.save(Goal.builder()
        .member(member)
        .startTime(req.getStartTime())
        .build()
    );
  }

  public Goal getById(Long id) {
    return goalRepository.findById(id).orElse(null);
  }

  public List<Goal> gets() {
    return goalRepository.findAll();
  }

  public List<Goal> getsByTime() {
    return goalRepository.findAllByOrderByActualTimeAsc();
  }

  public GoalResp goalResp(Goal goal) {
    return GoalResp.builder()
        .goalTime(formatDuration(goal.getActualTime()))
        .teamName(goal.getMember().getTeamName())
        .rank(goal.getRank())
        .build();
  }

  private String formatDuration(Duration duration) {
    long hours = duration.toHours();
    long minutes = duration.toMinutesPart();
    long seconds = duration.toSecondsPart();

    if (hours > 0) {
      return String.format("%d시간 %d분 %d초", hours, minutes, seconds);
    } else if (minutes > 0) {
      return String.format("%d분 %d초", minutes, seconds);
    } else {
      return String.format("%d초", seconds);
    }
  }


  public Goal getByMember(Member member) {
    return goalRepository.findByMember(member.getTeamName());

  }
}
